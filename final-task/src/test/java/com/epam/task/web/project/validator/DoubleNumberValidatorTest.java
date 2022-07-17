package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class DoubleNumberValidatorTest {

    private static final String VALID_DOUBLE_NUMBER = "100";
    private static final String INVALID_DOUBLE_NUMBER = "0";

    private final DoubleNumberValidator doubleNumberValidator = new DoubleNumberValidator();
    
    @Test
    public void isValidShouldReturnTrueWhenAppliedValidValue() {
        boolean actual = doubleNumberValidator.isValid(VALID_DOUBLE_NUMBER);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInValidValue() {
        boolean actual = doubleNumberValidator.isValid(INVALID_DOUBLE_NUMBER);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = doubleNumberValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = doubleNumberValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}

