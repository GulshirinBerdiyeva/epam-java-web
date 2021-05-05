package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class InputParameterValidator {

    private final String NUMBER_FORMAT = "\\d+\\.?\\d*";

    public boolean isValidString(String inputParameter) {
       return inputParameter != null && !inputParameter.trim().isEmpty();
    }

    public boolean isValidNumber(String inputParameter) {
        if (inputParameter == null || inputParameter.trim().isEmpty()) {
            return false;
        }

        return Pattern.matches(NUMBER_FORMAT, inputParameter);
    }

}
