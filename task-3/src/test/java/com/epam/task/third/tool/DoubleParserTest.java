package com.epam.task.third.tool;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DoubleParserTest {
    protected DoubleParser parser = new DoubleParser();

    private final static String VALID_LINE = "-2.0 -1.0 1.0 -5.0";
    private final static List<Double> EXPECTED = Arrays.asList(-2.0, -1.0, 1.0, -5.0);

    @Test
    public void testParseShouldParseLineToDoubleListWhenValidLineApplied(){
        //when
        List<Double> actual = parser.parse(VALID_LINE);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}