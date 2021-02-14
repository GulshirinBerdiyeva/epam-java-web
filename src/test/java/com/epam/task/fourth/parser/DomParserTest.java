package com.epam.task.fourth.parser;

import com.epam.task.fourth.exception.XmlException;
import com.epam.task.fourth.parser.dom.DomParser;

public class DomParserTest extends AbstractXmlParserTest{
    private DomParser parser = new DomParser();

    @Override
    public void testParseShouldReturnParsedPlantsWhenValidXmlFileApplied() throws XmlException {
        setParser(parser);
        super.testParseShouldReturnParsedPlantsWhenValidXmlFileApplied();
    }

    @Override
    public void testParseShouldThrowExceptionWhenInvalidXmlFileApplied() throws XmlException {
        setParser(parser);
        super.testParseShouldThrowExceptionWhenInvalidXmlFileApplied();
    }

}