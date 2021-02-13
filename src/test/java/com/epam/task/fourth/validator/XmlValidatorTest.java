package com.epam.task.fourth.validator;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private final String XSD_FILE = "files/orangery.xsd";
    private XmlValidator validator = new XmlValidator(XSD_FILE);
    private final String VALID_XML_FILE = "files/orangery.xml";
    private final String INVALID_XML_FILE = "files/orange.xml";

    @Test
    public void testIsValidShouldReturnTrueWhenGivenValidFiles(){
        //given
        boolean expected = true;

        //when
        boolean actual = validator.isValid(VALID_XML_FILE);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenGivenInValidFiles(){
        //given
        boolean expected = false;

        //when
        boolean actual = validator.isValid(INVALID_XML_FILE);

        //then
        Assert.assertEquals(expected, actual);
    }

}
