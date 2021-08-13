package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class PasswordValidator extends AbstractValidator {

    private static final String PASSWORD_REGEX = "(?=.*[A-ZА-ЯЁ])(?=.*[a-zа-яё])(?=.*\\d)[\\wА-Яа-яЁё]{8,20}";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(PASSWORD_REGEX, inputValue);
    }

}
