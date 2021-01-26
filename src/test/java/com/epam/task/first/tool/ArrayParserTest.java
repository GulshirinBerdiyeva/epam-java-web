package com.epam.task.first.tool;

import com.epam.task.first.entity.Array;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Test;

public class ArrayParserTest {

    private ArrayParser parser = new ArrayParser();

    @Test
    public void testParseShouldParseStringToArrayWhenValidLineApplied(){
        //given
        String line = "01  23 456  789";

        //when
        Array array = parser.parse(line);

        //then
        ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
        printer.print(array);
    }

}
