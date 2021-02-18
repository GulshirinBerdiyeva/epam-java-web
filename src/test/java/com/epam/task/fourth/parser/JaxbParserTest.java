package com.epam.task.fourth.parser;

import com.epam.task.fourth.exception.XmlException;
import com.epam.task.fourth.parser.jaxb.JaxbParser;

public class JaxbParserTest extends AbstractXmlParserTest{
    private final String XSD_FILE = "src/main/resources/orangery.xsd";
    private JaxbParser parser = new JaxbParser();

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