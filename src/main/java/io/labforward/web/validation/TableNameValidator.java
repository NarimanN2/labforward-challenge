package io.labforward.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TableNameValidator implements ConstraintValidator<TableName, String> {
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c) && c != '_') {
                return false;
            }
        }

        return true;
    }
}
