package com.epam.task.fourth.create;

import com.epam.task.fourth.creator.ParserFactory;
import com.epam.task.fourth.creator.ParserTypeException;
import com.epam.task.fourth.parser.dom.DomParser;
import com.epam.task.fourth.parser.jaxb.JaxbParser;
import com.epam.task.fourth.parser.sax.SaxParser;
import org.junit.Assert;
import org.junit.Test;

public class ParserFactoryTest {
    private ParserFactory factory = new ParserFactory();
    private final static String SAX = "sax";
    private final static String DOM = "Dom";
    private final static String JAXB = "JaxB";

    @Test
    public void testCreateShouldCreateSaxParserWhenStringSaxApplied() throws ParserTypeException {
        //when
        SaxParser actual = (SaxParser) factory.create(SAX);

        //then
        Assert.assertEquals(SaxParser.class, actual.getClass());
    }

    @Test
    public void testCreateShouldCreateDomParserWhenStringDomApplied() throws ParserTypeException {
        //when
        DomParser actual = (DomParser) factory.create(DOM);

        //then
        Assert.assertEquals(DomParser.class, actual.getClass());
    }

    @Test
    public void testCreateShouldCreateJaxbParserWhenStringJaxbApplied() throws ParserTypeException {
        //when
        JaxbParser actual = (JaxbParser) factory.create(JAXB);

        //then
        Assert.assertEquals(JaxbParser.class, actual.getClass());
    }

}