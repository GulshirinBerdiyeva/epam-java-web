package com.epam.task.fourth.parser.sax;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.validator.XmlException;
import com.epam.task.fourth.parser.XmlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements XmlParser {
    private final static Logger LOGGER = LogManager.getLogger(SaxParser.class);

    @Override
    public List<Plant> parse(String xmlFile) throws XmlException{
        try {
            PlantHandler plantHandler = new PlantHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(plantHandler);
            reader.parse(xmlFile);
            List<Plant> plants = plantHandler.getPlants();

            LOGGER.info(xmlFile + " parsed by SaxParser");
            return plants;
        } catch (SAXException | IOException e) {
            throw new XmlException(e.getMessage(), e);
        }
    }

}