package com.epam.task.fourth.parser.jaxb;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.entity.Orangery;
import com.epam.task.fourth.exception.XmlException;
import com.epam.task.fourth.parser.XmlParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

public class JaxbParser implements XmlParser {
    private final static Logger LOGGER = LogManager.getLogger(JaxbParser.class);
    private String xsdFile;

    public void setXsdFile(String xsdFile) {
        this.xsdFile = xsdFile;
    }

    @Override
    public List<Plant> parse(String xmlFile) throws XmlException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Orangery.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(xsdFile);
            Schema schema = schemaFactory.newSchema(schemaLocation);
            unmarshaller.setSchema(schema);

            File xmlLocation = new File(xmlFile);
            Orangery orangery = (Orangery)unmarshaller.unmarshal(xmlLocation);

            LOGGER.info(xmlFile + " parsed by JaxbParser");
            return orangery.getPlants();
        } catch (JAXBException | SAXException e) {
            throw new XmlException(e.getMessage(), e);
        }
    }

}