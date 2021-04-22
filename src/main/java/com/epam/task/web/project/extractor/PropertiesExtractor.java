package com.epam.task.web.project.extractor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesExtractor implements Extractor{
    @Override
    public Object extract(Object content) throws ExtractException {
        try {
            String fileName = String.valueOf(content);
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            Properties properties = new Properties();
            properties.load(inputStream);

            return properties;
        } catch (IOException e) {
            throw new ExtractException(e.getMessage(), e);
        }

    }
}
