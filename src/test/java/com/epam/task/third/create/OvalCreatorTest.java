package com.epam.task.third.create;

import com.epam.task.third.data.DataException;
import com.epam.task.third.data.TxtFileReader;
import com.epam.task.third.entity.Oval;
import com.epam.task.third.tool.Parser;
import com.epam.task.third.tool.Validator;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class OvalCreatorTest {
    private final List<String> DATA = Arrays.asList("5.0 3.0 0.0 5.0",
                                                    "2.n -4 4",
                                                    "6 2.5 0 ?",
                                                    "-2.0 -1.0 1.0 -5.0");
    private final Boolean VALID = true,
                          INVALID = false;
    private final List<Double> POINTS = Arrays.asList(5.0, 3.0, 0.0, 5.0,
                                                     -2.0, -1.0, 1.0, -5.0);


    @Test
    public void testCreateShouldCreateValidOvalsWhenFileNameApplied() throws DataException, IOException {
        TxtFileReader reader = Mockito.mock(TxtFileReader.class);
        when(reader.read(anyString())).thenReturn(DATA);

        Validator validator = Mockito.mock(Validator.class);
        when(validator.isValid(anyString())).thenReturn(VALID, INVALID, INVALID, VALID);

        Parser parser = Mockito.mock(Parser.class);
        when(parser.parse(anyString())).thenReturn(POINTS);

        OvalCreator creator = new OvalCreator(reader, validator, parser);
        List<Oval> ovals = creator.create(anyString());

        System.out.println(ovals);

    }

}
