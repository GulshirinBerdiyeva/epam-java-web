package com.epam.task.third.create;

import com.epam.task.third.data.DataException;
import com.epam.task.third.data.TxtFileReader;
import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import com.epam.task.third.tool.Parser;
import com.epam.task.third.tool.Validator;
import org.junit.Assert;
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
                                                    "-6.0 2.5 3.0 4.0",
                                                    "6 2.5 0 ?",
                                                    "-2.0 -1.0 1.0 -5.0",
                                                    "7.0 3.5 -2.0 5.5");
    private final Boolean VALID = true;
    private final Boolean INVALID = false;
    private final List<Double> LIST1 = Arrays.asList(5.0, 3.0, 0.0, 5.0);
    private final List<Double> LIST2 = Arrays.asList(-6.0, 2.5, 3.0, 4.0);
    private final List<Double> LIST3 = Arrays.asList(-2.0, -1.0, 1.0, -5.0);
    private final List<Double> LIST4 = Arrays.asList(7.0, 3.5, -2.0, 5.5);

    private final List<Oval> EXPECTED = Arrays.asList(new Oval(42, new Point(5.0, 3.0), new Point(0.0, 5.0)),
                                                      new Oval(42, new Point(-6.0, 2.5), new Point(3.0, 4.0)),
                                                      new Oval(42, new Point(-2.0, -1.0), new Point(1.0, -5.0)),
                                                      new Oval(42, new Point(7.0, 3.5), new Point(-2.0, 5.5)));

    @Test
    public void testCreateShouldCreateValidOvalsWhenFileNameApplied() throws DataException, IOException {
        TxtFileReader reader = Mockito.mock(TxtFileReader.class);
        when(reader.read(anyString())).thenReturn(DATA);

        Validator validator = Mockito.mock(Validator.class);
        when(validator.isValid(anyString())).thenReturn(VALID,INVALID, VALID, INVALID, VALID, VALID);

        Parser parser = Mockito.mock(Parser.class);
        when(parser.parse(anyString())).thenReturn(LIST1, LIST2, LIST3, LIST4);

        OvalCreator creator = new OvalCreator(reader, validator, parser);
        List<Oval> actual = creator.create(anyString());

        Assert.assertEquals(EXPECTED, actual);
    }

}
