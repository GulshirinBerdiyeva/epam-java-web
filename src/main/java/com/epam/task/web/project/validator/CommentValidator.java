package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class CommentValidator extends AbstractValidator {

    private static final String COMMENT_REGEX = "(?=.*[A-ZА-ЯЁa-zа-яё0-9]).{1,100}";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(COMMENT_REGEX, inputValue);
    }

}
