package com.epam.task.fourth.parser;

import com.epam.task.fourth.exception.XmlException;
import com.epam.task.fourth.parser.sax.SaxParser;

public class SaxParserTest extends AbstractXmlParserTest{
    private SaxParser parser = new SaxParser();

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
