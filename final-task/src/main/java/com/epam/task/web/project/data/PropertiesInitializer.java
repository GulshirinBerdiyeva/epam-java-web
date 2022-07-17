package com.epam.task.web.project.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesInitializer {

    public Properties init(String resourceName) throws DataException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName)) {

            Properties properties = new Properties();

            properties.load(inputStream);

            return properties;

        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

}
