package com.epam.task.first.tool;

import org.junit.Assert;
import org.junit.Test;

public class ArrayValidatorTest {
    private ArrayValidator validator = new ArrayValidator();
    private final String line = "2021:)";

    @Test
    public void testIsValidShouldReturnFalseWhenInvalidLineApplied(){
        //when
        boolean actual = validator.isValid(line);

        //then
        Assert.assertFalse(actual);
    }

}
