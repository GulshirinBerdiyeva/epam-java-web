package com.epam.task.first.check;

import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Test;

public class ArrayParserTest {

    ArrayParser parser = new ArrayParser();
    ArrayValidator validator = new ArrayValidator();

    @Test
    public void testParseShouldParseStringToArrayWhenAppliedStringOfNumbers(){
        //given
        String line = " 12 55 6 898  10 ";

        //when
        boolean actual = validator.isValid(line);

        //then
        if (actual){
            ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
            printer.print(parser.parse(line));
        }else{
            System.out.println("Invalid data!");
        }
    }

}
