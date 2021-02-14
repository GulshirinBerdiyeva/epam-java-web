package com.epam.task.fourth.parser;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.entity.PlantVisualParameters;
import com.epam.task.fourth.entity.Rosacea;
import com.epam.task.fourth.exception.XmlException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractXmlParserTest {
    private XmlParser parser;
    private final String VALID_XML_FILE = "files/orangery.xml";
    private final String INVALID_XML_FILE = "files/orange.xml";

    private final List<Plant> EXPECTED = Arrays.asList(new Plant("p01","tulip", new PlantVisualParameters("yellow", 1, 20)),
                                                       new Plant("p02","chamomile", new PlantVisualParameters("blue", 7, 15)),
                                                       new Rosacea("r01","rose", new PlantVisualParameters("red", 5, 50), true),
                                                       new Rosacea("r02","spirea", new PlantVisualParameters("white", 7, 100), false));


    public void setParser(XmlParser parser){
        this.parser = parser;
    }

    @Test
    public void testParseShouldReturnParsedPlantsWhenValidXmlFileApplied() throws XmlException{
        //when
        List<Plant> actual = parser.parse(VALID_XML_FILE);

        //then
        System.out.println(actual);
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test
    public void testParseShouldThrowExceptionWhenInvalidXmlFileApplied() throws XmlException{
        Assert.assertThrows(XmlException.class, ()-> parser.parse(INVALID_XML_FILE));
    }

}