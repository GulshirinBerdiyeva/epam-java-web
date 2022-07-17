package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class DiscountValidatorTest {

    private static final String VALID_DISCOUNT = "50";
    private static final String INVALID_DISCOUNT = "-50";

    private final DiscountValidator discountValidator = new DiscountValidator();
    
    @Test
    public void isValidShouldReturnTrueWhenAppliedValidValue() {
        boolean actual = discountValidator.isValid(VALID_DISCOUNT);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedInValidValue() {
        boolean actual = discountValidator.isValid(INVALID_DISCOUNT);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = discountValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = discountValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}
