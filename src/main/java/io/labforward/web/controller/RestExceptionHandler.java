package io.labforward.web.controller;

import io.labforward.categories.exception.ResourceNotFoundException;
import io.labforward.web.dto.ExceptionJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionJson> handleUnknownException(Exception exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        ExceptionJson exceptionJson = new ExceptionJson(
                Collections.singletonList("Unknown server exception"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(exceptionJson, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionJson> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        ExceptionJson exceptionJson = new ExceptionJson(
                Collections.singletonList(exception.getMessage()),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(exceptionJson, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(exception.getMessage(), exception);
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> messages = new LinkedList<>();

        for (FieldError fieldError : fieldErrors) {
            String message = messageSource.getMessage(fieldError, null);
            messages.add(message);
        }

        ExceptionJson exceptionJson = new ExceptionJson(messages, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionJson, HttpStatus.BAD_REQUEST);
    }
}
