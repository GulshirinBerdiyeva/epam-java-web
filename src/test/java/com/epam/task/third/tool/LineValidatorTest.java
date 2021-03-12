package com.epam.task.third.tool;

import org.junit.Assert;
import org.junit.Test;

public class LineValidatorTest {
    private LineValidator validator = new LineValidator();

    private final static String VALID_LINE = "-2.0 -1.0 1.0 -5.0";
    private final boolean expectedTrue = true;

    private final static String INVALID_LINE = "-?.0 -1.0 1.s -5.0";
    private final boolean expectedFalse = false;

    @Test
    public void testISValidShouldReturnTrueWhenValidLineApplied(){
        //when
        Boolean actual = validator.isValid(VALID_LINE);

        //then
        Assert.assertEquals(expectedTrue, actual);
    }

    @Test
    public void testISValidShouldReturnFalseWhenInvalidLineApplied(){
        //when
        Boolean actual = validator.isValid(INVALID_LINE);

        //then
        Assert.assertEquals(expectedFalse, actual);
    }

}