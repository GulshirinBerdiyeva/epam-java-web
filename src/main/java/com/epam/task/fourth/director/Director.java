package com.epam.task.fourth.director;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.exception.XmlException;
import com.epam.task.fourth.parser.XmlParser;
import com.epam.task.fourth.validator.XmlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Director {
    private final static Logger LOGGER = LogManager.getLogger(Director.class);
    private XmlValidator validator;
    private XmlParser parser;

    public Director(XmlValidator validator, XmlParser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    public Optional<List<Plant>> parse(String xmlFile) {
        try {
            boolean isValid = validator.isValid(xmlFile);
            if (isValid){
                List<Plant> plants = parser.parse(xmlFile);
                LOGGER.info(xmlFile + " is valid and parsed");
                return Optional.of(plants);
            }
            LOGGER.info(xmlFile + " isn't valid");
            return Optional.empty();

        } catch (XmlException e) {
            LOGGER.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

}