package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class PasswordValidatorTest {

    private static final String VALID_PASSWORD = "pAssWord_42";
    private static final String INVALID_PASSWORD = " d%$^A*&/*7-";

    private final PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidValue() {
        boolean actual = passwordValidator.isValid(VALID_PASSWORD);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInValidValue() {
        boolean actual = passwordValidator.isValid(INVALID_PASSWORD);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = passwordValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = passwordValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}