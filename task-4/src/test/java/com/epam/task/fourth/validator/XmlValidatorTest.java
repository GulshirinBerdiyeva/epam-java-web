package com.epam.task.fourth.validator;

import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private final static String XSD_FILE = "src/main/resources/orangery.xsd";
    private XmlValidator validator = new XmlValidator(XSD_FILE);

    private final static String VALID_XML_FILE = "src/main/resources/orangery.xml";
    private final static String INVALID_XML_FILE = "src/main/resources/orange.xml";

    @Test
    public void testIsValidShouldReturnTrueWhenGivenValidFiles() throws XmlException {
        //when
        boolean actual = validator.isValid(VALID_XML_FILE);

        //then
        Assert.assertTrue(actual);
    }

    @Test(expected = XmlException.class)
    public void testIsValidShouldThrowXmlExceptionWhenInValidFileApplied() throws XmlException {
        //when
        boolean actual = validator.isValid(INVALID_XML_FILE);
    }

}