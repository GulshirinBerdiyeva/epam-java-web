package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class InputParameterValidator {

    private static final String NUMBER_FORMAT = "\\d+\\.?\\d*";

    public boolean isStringValid(String inputParameter) {
       return inputParameter != null && !inputParameter.trim().isEmpty();
    }

    public boolean isNumberValid(String inputParameter) {
        if (inputParameter == null || inputParameter.trim().isEmpty()) {
            return false;
        }

        return Pattern.matches(NUMBER_FORMAT, inputParameter);
    }

}