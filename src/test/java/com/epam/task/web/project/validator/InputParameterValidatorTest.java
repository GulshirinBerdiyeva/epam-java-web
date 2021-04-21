package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InputParameterValidatorTest extends AbstractValidatorTest{

    private static final String VALID_STRING = "Hello";
    private static final boolean EXPECTED_TRUE = true;

    @BeforeClass
    public static void setValidator() {
        AbstractValidatorTest.setValidator(new InputParameterValidator());
    }

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidString() {
        boolean actual = getValidator().isValid(VALID_STRING);

        Assert.assertEquals(EXPECTED_TRUE, actual);
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
