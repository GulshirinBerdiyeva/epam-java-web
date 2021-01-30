package com.epam.task.first.tool;

import com.epam.task.first.entity.Array;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Assert;
import org.junit.Test;

public class ArrayParserTest {
    private ArrayParser parser = new ArrayParser();
    private ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
    private final String line = "01  42 4 056  18 -9";
    private final Array expectedArray = new Array(1, 42, 4, 56, 18, -9);

    @Test
    public void testParseShouldParseStringToArrayWhenValidLineApplied(){
        //when
        Array actualArray = parser.parse(line);

        //then
        Assert.assertEquals(expectedArray, actualArray);
        printer.print(actualArray);
        printer.print(expectedArray);
    }

}
