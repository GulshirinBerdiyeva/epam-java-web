package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class FullNameValidatorTest {

    private static final String VALID_FULL_NAME = "Jack London";
    private static final String INVALID_FULL_NAME = "J.";

    private final FullNameValidator fullNameValidator = new FullNameValidator();

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidValue() {
        boolean actual = fullNameValidator.isValid(VALID_FULL_NAME);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInValidValue() {
        boolean actual = fullNameValidator.isValid(INVALID_FULL_NAME);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = fullNameValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = fullNameValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}