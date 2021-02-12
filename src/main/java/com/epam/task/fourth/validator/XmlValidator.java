package com.epam.task.fourth.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    private final static Logger LOGGER = LogManager.getLogger(XmlValidator.class);

    public Boolean isValid(String xsdFile, String xmlFile){
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(xsdFile);

        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFile);
            validator.validate(source);

            LOGGER.info(xmlFile + " is valid");
            return true;
        } catch (SAXException e) {
            LOGGER.error("validation " + xmlFile + " isn't valid because " + e.getMessage());
            return false;
        } catch (IOException e) {
            LOGGER.error(xmlFile + " isn't valid because " + e.getMessage());
            return false;
        }
    }

}
