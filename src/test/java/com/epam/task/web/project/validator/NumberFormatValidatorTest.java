package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumberFormatValidatorTest extends AbstractValidatorTest{

    private static final String VALID_NUMBER = "42.42";
    private static final String INVALID_NUMBER = "12.4.5";

    private static final boolean EXPECTED_TRUE = true;
    private static final boolean EXPECTED_FALSE = false;

    @BeforeClass
    public static void setValidator() {
        AbstractValidatorTest.setValidator(new NumberFormatValidator());
    }

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidNumberFormat() {
        boolean actual = getValidator().isValid(VALID_NUMBER);

        Assert.assertEquals(EXPECTED_TRUE, actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInvalidNumberFormat() {
        boolean actual = getValidator().isValid(INVALID_NUMBER);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Override
    public void isValidShouldReturnFalseWhenAppliedEmptyString() {
        super.isValidShouldReturnFalseWhenAppliedEmptyString();
    }

    @Override
    public void isValidShouldReturnFalseWhenAppliedNull() {
        super.isValidShouldReturnFalseWhenAppliedNull();
    }

}
