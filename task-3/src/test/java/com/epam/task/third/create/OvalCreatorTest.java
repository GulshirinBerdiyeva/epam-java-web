package com.epam.task.third.create;

import com.epam.task.third.data.DataException;
import com.epam.task.third.data.DataReader;
import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import com.epam.task.third.logic.IdGenerator;
import com.epam.task.third.tool.DoubleParser;
import com.epam.task.third.tool.LineValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class OvalCreatorTest {
    private final static List<String> DATA = Arrays.asList("5.0 3.0 0.0 5.0",
                                                           "2.n -4 4",
                                                           "-6.0 2.5 3.0 4.0",
                                                           "6 2.5 0 ?",
                                                           "-2.0 -1.0 1.0 -5.0",
                                                           "7.0 3.5 -2.0 5.5");
    private final static boolean VALID = true;
    private final static boolean INVALID = false;
    private final static List<Double> FIRST_LIST = Arrays.asList(5.0, 3.0, 0.0, 5.0);
    private final static List<Double> SECOND_LIST = Arrays.asList(-6.0, 2.5, 3.0, 4.0);
    private final static List<Double> THIRD_LIST = Arrays.asList(-2.0, -1.0, 1.0, -5.0);
    private final static List<Double> FOURTH_LIST = Arrays.asList(7.0, 3.5, -2.0, 5.5);

    private final static List<Oval> EXPECTED = Arrays.asList(
                                                        new Oval(42, new Point(5.0, 3.0), new Point(0.0, 5.0)),
                                                        new Oval(4, new Point(-6.0, 2.5), new Point(3.0, 4.0)),
                                                        new Oval(2, new Point(-2.0, -1.0), new Point(1.0, -5.0)),
                                                        new Oval(24, new Point(7.0, 3.5), new Point(-2.0, 5.5)));

    @Test
    public void testCreateShouldCreateValidOvalsWhenFileNameApplied() throws DataException, IOException {
        DataReader reader = Mockito.mock(DataReader.class);
        when(reader.read(anyString())).thenReturn(DATA);

        LineValidator validator = Mockito.mock(LineValidator.class);
        when(validator.isValid(anyString())).thenReturn(VALID,INVALID, VALID, INVALID, VALID, VALID);

        DoubleParser parser = Mockito.mock(DoubleParser.class);
        when(parser.parse(anyString())).thenReturn(FIRST_LIST, SECOND_LIST, THIRD_LIST, FOURTH_LIST);

        IdGenerator idGenerator = Mockito.mock(IdGenerator.class);
        when(idGenerator.generateId()).thenReturn(42, 4, 2, 24);

        OvalCreator creator = new OvalCreator(reader, validator, parser, idGenerator);
        List<Oval> actual = creator.create(anyString());

        Assert.assertEquals(EXPECTED, actual);
    }

}