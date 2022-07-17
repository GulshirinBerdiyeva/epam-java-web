package com.epam.task.fourth.creator;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.validator.XmlException;
import com.epam.task.fourth.parser.XmlParser;
import com.epam.task.fourth.validator.XmlValidator;

import java.util.ArrayList;
import java.util.List;

public class Director {
    private XmlValidator validator;
    private XmlParser parser;

    public Director(XmlValidator validator, XmlParser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    public List<Plant> parse(String xmlFile) throws XmlException {
        List<Plant> plants = new ArrayList<>();
        boolean isValid = validator.isValid(xmlFile);
        if (isValid){
            plants = parser.parse(xmlFile);
        }

        return plants;
    }

}