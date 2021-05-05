package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class NumberFormatValidator implements Validator{

    private final String NUMBER_FORMAT = "\\d+\\.?\\d*";

    @Override
    public boolean isValid(String inputParameter) {
        if (inputParameter == null || inputParameter.isEmpty()) {
            return false;
        }

        return Pattern.matches(NUMBER_FORMAT, inputParameter);
    }

}
