package com.epam.task.third.tool;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ParserTest {
    protected Parser parser = new Parser();
    private final String VALID_LINE = "-2.0 -1.0 1.0 -5.0";
    private final List<Double> EXPECTED = Arrays.asList(-2.0, -1.0, 1.0, -5.0);

    @Test
    public void testParseShouldParseLineToDoubleListWhenValidLineApplied(){
        //when
        List<Double> actual = parser.parse(VALID_LINE);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}
