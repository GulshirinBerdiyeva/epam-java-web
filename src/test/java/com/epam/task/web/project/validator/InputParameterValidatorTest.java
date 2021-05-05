package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class InputParameterValidatorTest {

    private static final String VALID_STRING = "Hello";
    private static final String EMPTY = "";
    private static final String NULL = null;
    private static final String VALID_NUMBER = "42.42";
    private static final String INVALID_NUMBER = "12.3.4";
    private static final boolean EXPECTED_TRUE = true;
    private static final boolean EXPECTED_FALSE = false;

    private InputParameterValidator validator = new InputParameterValidator();

    @Test
    public void isValidStringShouldReturnTrueWhenAppliedValidString() {
        boolean actual = validator.isValidString(VALID_STRING);

        Assert.assertEquals(EXPECTED_TRUE, actual);
    }

    @Test
    public void isValidStringShouldReturnFalseWhenAppliedEmptyString() {
        boolean actual = validator.isValidString(EMPTY);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isValidStringShouldReturnFalseWhenAppliedNull() {
        boolean actual = validator.isValidString(NULL);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isValidNumberShouldReturnTrueWhenAppliedValidNumber() {
        boolean actual = validator.isValidNumber(VALID_NUMBER);

        Assert.assertEquals(EXPECTED_TRUE, actual);
    }

    @Test
    public void isValidNumberShouldReturnFalseWhenAppliedInvalidNumber() {
        boolean actual = validator.isValidNumber(INVALID_NUMBER);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isValidNumberShouldReturnFalseWhenAppliedEmptyString() {
        boolean actual = validator.isValidNumber(EMPTY);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isValidNumberShouldReturnFalseWhenAppliedNull() {
        boolean actual = validator.isValidNumber(NULL);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

}
