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

    private final InputParameterValidator validator = new InputParameterValidator();

    @Test
    public void isStringValidShouldReturnTrueWhenAppliedValidString() {
        boolean actual = validator.isStringValid(VALID_STRING);

        Assert.assertEquals(EXPECTED_TRUE, actual);
    }

    @Test
    public void isStringValidShouldReturnFalseWhenAppliedEmptyString() {
        boolean actual = validator.isStringValid(EMPTY);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isStringValidShouldReturnFalseWhenAppliedNull() {
        boolean actual = validator.isStringValid(NULL);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isNumberValidShouldReturnTrueWhenAppliedValidNumber() {
        boolean actual = validator.isNumberValid(VALID_NUMBER);

        Assert.assertEquals(EXPECTED_TRUE, actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedInvalidNumber() {
        boolean actual = validator.isNumberValid(INVALID_NUMBER);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyString() {
        boolean actual = validator.isNumberValid(EMPTY);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedNull() {
        boolean actual = validator.isNumberValid(NULL);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

}