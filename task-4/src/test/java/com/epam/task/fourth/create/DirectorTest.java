package com.epam.task.fourth.create;

import com.epam.task.fourth.creator.Director;
import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.entity.PlantVisualParameters;
import com.epam.task.fourth.entity.Rosacea;
import com.epam.task.fourth.validator.XmlException;
import com.epam.task.fourth.parser.XmlParser;
import com.epam.task.fourth.validator.XmlValidator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class DirectorTest {
    private final static String VALID_XML_FILE = "src/main/resources/orangery.xml";
    private final static String INVALID_XML_FILE = "src/main/resources/orange.xml";

    private final static List<Plant> EXPECTED_PLANTS = Arrays.asList(
                                                    new Plant("p01","tulip", new PlantVisualParameters("yellow", 1, 20)),
                                                    new Plant("p02","chamomile", new PlantVisualParameters("blue", 7, 15)),
                                                    new Rosacea("r01","rose", new PlantVisualParameters("red", 5, 50), true),
                                                    new Rosacea("r02","spirea", new PlantVisualParameters("white", 7, 100), false));

    @Test
    public void testParseShouldParseXmlFileWhenValidXmlFileApplied() throws XmlException {
        //given
        XmlValidator validator = Mockito.mock(XmlValidator.class);
        when(validator.isValid(VALID_XML_FILE)).thenReturn(true);

        XmlParser parser = Mockito.mock(XmlParser.class);
        when(parser.parse(VALID_XML_FILE)).thenReturn(EXPECTED_PLANTS);

        //when
        Director director = new Director(validator, parser);
        List<Plant> actual = director.parse(VALID_XML_FILE);

        //then
        Assert.assertEquals(EXPECTED_PLANTS, actual);
    }

    @Test(expected = XmlException.class)
    public void testParseShouldThrowXmlExceptionWhenInvalidXmlFileApplied() throws XmlException {
        //given
        XmlValidator validator = Mockito.mock(XmlValidator.class);
        when(validator.isValid(INVALID_XML_FILE)).thenThrow(XmlException.class);

        XmlParser parser = Mockito.mock(XmlParser.class);
        when(parser.parse(INVALID_XML_FILE)).thenThrow(XmlException.class);

        //when
        Director director = new Director(validator, parser);
        List<Plant> actual = director.parse(INVALID_XML_FILE);
    }

}