package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class DiscountValidator extends AbstractValidator {

    private static final String DISCOUNT_REGEX = "[0-9]|[1-9][0-9]|100";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(DISCOUNT_REGEX, inputValue);
    }

}
