package com.epam.task.first.tool;

import org.junit.Assert;
import org.junit.Test;

public class ArrayValidatorTest {
    private ArrayValidator validator = new ArrayValidator();
    private final String LINE = "2021:)";

    @Test
    public void testIsValidShouldReturnFalseWhenInvalidLineApplied(){
        //when
        boolean actual = validator.isValid(LINE);

        //then
        Assert.assertFalse(actual);
    }

}
