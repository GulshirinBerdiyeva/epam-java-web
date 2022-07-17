package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class CommentValidatorTest {

    private static final String VALID_COMMENT = "Leave here best comment";
    private static final String INVALID_COMMENT = " %$^*&/*-";

    private final CommentValidator commentValidator = new CommentValidator();

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidValue() {
        boolean actual = commentValidator.isValid(VALID_COMMENT);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInValidValue() {
        boolean actual = commentValidator.isValid(INVALID_COMMENT);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = commentValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = commentValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}