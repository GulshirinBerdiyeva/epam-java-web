package com.epam.task.first.tool;

import com.epam.task.first.entity.Array;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Assert;
import org.junit.Test;

public class ArrayParserTest {
    private ArrayParser parser = new ArrayParser();
    private ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
    private final String LINE = "01  42 4 056  18 -9";
    private final Array EXPECTED_ARRAY = new Array(1, 42, 4, 56, 18, -9);

    @Test
    public void testParseShouldParseStringToArrayWhenValidLineApplied(){
        //when
        Array actualArray = parser.parse(LINE);

        //then
        Assert.assertEquals(EXPECTED_ARRAY, actualArray);
        printer.print(actualArray);
        printer.print(EXPECTED_ARRAY);
    }

}
