package io.labforward.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TableNameValidator.class)
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface TableName {
    String message() default "Invalid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
