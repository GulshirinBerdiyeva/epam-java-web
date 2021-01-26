package com.epam.task.first.create;

import com.epam.task.first.data.FileException;
import com.epam.task.first.data.TxtFileReader;
import com.epam.task.first.entity.Array;
import com.epam.task.first.tool.ArrayParser;
import com.epam.task.first.tool.ArrayValidator;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ArrayCreatorTest {

    @Test
    public void testCreateShouldCreateListArrayWhenDataApplied() throws FileException {
        //given
        final List<String> DATA = Arrays.asList("12 45 78", "23v 56s 89!", "7 77 7", "01 02 03");
        final Boolean VALID = true,
                      INVALID = false;
        final Array VALID_ARRAY = new Array(12, 45, 78);
        final Array VALID_ARRAY2 = new Array(7, 77, 7);
        final Array VALID_ARRAY3 = new Array(01, 02, 03);

        //when
        TxtFileReader reader = Mockito.mock(TxtFileReader.class);
        when(reader.read(anyString())).thenReturn(DATA);

        ArrayValidator validator = Mockito.mock(ArrayValidator.class);
        when(validator.isValid(anyString())).thenReturn(VALID, INVALID, VALID, VALID);

        ArrayParser parser = Mockito.mock(ArrayParser.class);
        when(parser.parse(anyString())).thenReturn(VALID_ARRAY, VALID_ARRAY2, VALID_ARRAY3);

        ArrayCreator creator = new ArrayCreator(reader, validator, parser);
        List<Array> arrayList = creator.create(anyString());

        //then
        ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
        for (Array line : arrayList){
            printer.print(line);
        }
    }

}
