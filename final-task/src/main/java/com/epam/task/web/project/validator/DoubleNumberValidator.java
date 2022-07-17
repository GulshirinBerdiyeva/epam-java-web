package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class DoubleNumberValidator extends AbstractValidator {

    private static final String DOUBLE_NUMBER_REGEX = "(([1-9]|[1-9][0-9])(\\.\\d{1,2})?)|100(\\.0{1,2})?";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(DOUBLE_NUMBER_REGEX, inputValue);
    }

}
