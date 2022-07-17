package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class NumberValidator extends AbstractValidator {

    private static final String NUMBER_REGEX = "[0-9]|[1-9][0-9]|[1-9][0-9][0-9]|1000";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(NUMBER_REGEX, inputValue);
    }

}
