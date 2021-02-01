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
                                                    "6 2.5 0 ?",
                                                    "-2.0 -1.0 1.0 -5.0");
    private final Boolean VALID = true,
                          INVALID = false;
    private final List<Double> LIST1 = Arrays.asList(5.0, 3.0, 0.0, 5.0);
    private final List<Double> LIST2 = Arrays.asList(-2.0, -1.0, 1.0, -5.0);
    private final Point POINT1 = new Point(5.0, 3.0);
    private final Point POINT2 = new Point(0.0, 5.0);
    private final Point POINT3 = new Point(-2.0, -1.0);
    private final Point POINT4 = new Point(1.0, -5.0);
    private final Oval OVAL1 = new Oval(POINT1, POINT2);
    private final Oval OVAL2 = new Oval(POINT3, POINT4);
    private final List<Oval> EXPECTED = Arrays.asList(OVAL1, OVAL2);

    @Test
    public void testCreateShouldCreateValidOvalsWhenFileNameApplied() throws DataException, IOException {
        TxtFileReader reader = Mockito.mock(TxtFileReader.class);
        when(reader.read(anyString())).thenReturn(DATA);

        Validator validator = Mockito.mock(Validator.class);
        when(validator.isValid(anyString())).thenReturn(VALID, INVALID, INVALID, VALID);

        Parser parser = Mockito.mock(Parser.class);
        when(parser.parse(anyString())).thenReturn(LIST1, LIST2);

        OvalCreator creator = new OvalCreator(reader, validator, parser);
        List<Oval> actual = creator.create(anyString());

        Assert.assertEquals(EXPECTED, actual);
    }

}
