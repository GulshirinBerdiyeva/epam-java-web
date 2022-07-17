package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class NumberValidatorTest {

    private static final String VALID_NUMBER = "1000";
    private static final String INVALID_NUMBER = "-100";

    private final NumberValidator numberValidator = new NumberValidator();

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidValue() {
        boolean actual = numberValidator.isValid(VALID_NUMBER);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInValidValue() {
        boolean actual = numberValidator.isValid(INVALID_NUMBER);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = numberValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = numberValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}