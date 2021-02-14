package com.epam.task.fourth.validator;

import com.epam.task.fourth.exception.XmlException;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private final String XSD_FILE = "files/orangery.xsd";
    private XmlValidator validator = new XmlValidator(XSD_FILE);
    private final String VALID_XML_FILE = "files/orangery.xml";
    private final String INVALID_XML_FILE = "files/orange.xml";

    @Test
    public void testIsValidShouldReturnTrueWhenGivenValidFiles() throws XmlException {
        //when
        boolean actual = validator.isValid(VALID_XML_FILE);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenGivenInValidFiles() {
        Assert.assertThrows(XmlException.class, ()->validator.isValid(INVALID_XML_FILE));
    }

}
