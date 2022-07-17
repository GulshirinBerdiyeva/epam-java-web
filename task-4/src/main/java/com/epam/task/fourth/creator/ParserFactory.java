package com.epam.task.fourth.creator;

import com.epam.task.fourth.parser.XmlParser;
import com.epam.task.fourth.parser.dom.DomParser;
import com.epam.task.fourth.parser.jaxb.JaxbParser;
import com.epam.task.fourth.parser.sax.SaxParser;

public class ParserFactory {

    private enum ParserType{
        SAX, DOM, JAXB
    }

    public XmlParser create(String parserType) throws ParserTypeException {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type){
            case SAX:
                return new SaxParser();
            case DOM:
                return new DomParser();
            case JAXB:
                return new JaxbParser();
            default:
                throw new ParserTypeException();
        }
    }

}