package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class TitleValidatorTest {

    private static final String VALID_TITLE = "mUsic's title";
    private static final String INVALID_TITLE = "T it$l' ";

    private final TitleValidator titleValidator = new TitleValidator();

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidValue() {
        boolean actual = titleValidator.isValid(VALID_TITLE);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInValidValue() {
        boolean actual = titleValidator.isValid(INVALID_TITLE);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = titleValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = titleValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}
