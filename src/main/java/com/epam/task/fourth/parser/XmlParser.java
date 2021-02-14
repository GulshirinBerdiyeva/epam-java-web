package com.epam.task.fourth.parser;

import com.epam.task.fourth.entity.Plant;
import com.epam.task.fourth.exception.XmlException;

import java.util.List;

public interface XmlParser {
    List<Plant> parse(String xmlFile) throws XmlException;
}