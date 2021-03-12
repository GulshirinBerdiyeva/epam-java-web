package com.epam.task.fourth.parser;

import com.epam.task.fourth.validator.XmlException;
import com.epam.task.fourth.parser.jaxb.JaxbParser;

public class JaxbParserTest extends AbstractXmlParserTest{
    private JaxbParser parser = new JaxbParser();
    private final static String XSD_FILE = "src/main/resources/orangery.xsd";

    @Override
    public void testParseShouldReturnParsedPlantsWhenValidXmlFileApplied() throws XmlException {
        parser.setXsdFile(XSD_FILE);
        setParser(parser);
        super.testParseShouldReturnParsedPlantsWhenValidXmlFileApplied();
    }

    @Override
    public void testParseShouldThrowExceptionWhenInvalidXmlFileApplied() throws XmlException {
        parser.setXsdFile(XSD_FILE);
        setParser(parser);
        super.testParseShouldThrowExceptionWhenInvalidXmlFileApplied();
    }

}