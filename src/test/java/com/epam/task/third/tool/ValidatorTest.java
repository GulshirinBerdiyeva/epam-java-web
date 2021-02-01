package com.epam.task.third.tool;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {
    private Validator validator = new Validator();
    private final String VALID_LINE = "-2.0 -1.0 1.0 -5.0";
    private final Boolean EXPECTED_TRUE = true;
    private final String INVALID_LINE = "?.0 -4.0.4 6.0 3.0";
    private final Boolean EXPECTED_FALSE = false;

    @Test
    public void testISValidShouldReturnTrueWhenValidLineApplied(){
        //when
        Boolean actual = validator.isValid(VALID_LINE);

        //then
        Assert.assertEquals(EXPECTED_TRUE, actual);
    }

    @Test
    public void testISValidShouldReturnFalseWhenInvalidLineApplied(){
        //when
        Boolean actual = validator.isValid(INVALID_LINE);

        //then
        Assert.assertEquals(EXPECTED_FALSE, actual);
    }

}
