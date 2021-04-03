package io.labforward.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FieldNamesValidator implements ConstraintValidator<FieldNames, Map> {
    @Override
    public boolean isValid(Map map, ConstraintValidatorContext constraintValidatorContext) {
        List<String> fieldNames = new ArrayList<>(map.keySet());

        for (String name : fieldNames) {
            char[] chars = name.toCharArray();

            for (char c : chars) {
                if (!Character.isLetter(c) && c != '_') {
                    return false;
                }
            }
        }

        return true;
    }
}
