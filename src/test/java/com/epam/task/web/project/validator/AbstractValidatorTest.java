package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractValidatorTest {

    private static Validator validator;

    private static final String EMPTY = "";
    private static final String NULL = null;
    private static final boolean EXPECTED_FALSE = false;

    public static void setValidator(Validator validator) {
        AbstractValidatorTest.validator = validator;
    }

    public Validator getValidator() {
        return validator;
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedEmptyString() {
        boolean actual = validator.isValid(EMPTY);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedNull() {
        boolean actual = validator.isValid(NULL);

        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

}
