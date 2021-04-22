package com.epam.task.web.project.connection;

import com.epam.task.web.project.extractor.ExtractException;
import com.epam.task.web.project.extractor.PropertiesExtractor;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String FILE_NAME = "connection.properties";
    private static final String URL = "url";
    private static final String DATA_BASE = "dataBase";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private String url;
    private String dataBase;
    private String user;
    private String password;

    public ConnectionFactory () {
        getProperties();
    }

    public Connection create() throws ConnectionException{
        try {
            Connection connection = DriverManager.getConnection(url + dataBase, user, password);
            return connection;

        } catch (SQLException e) {
            throw new ConnectionException("Couldn't get access to the database!", e);
        }
    }

    private void getProperties() {
        try {
            DriverManager.registerDriver(new Driver());
            PropertiesExtractor propertiesExtractor = new PropertiesExtractor();
            Properties properties = (Properties) propertiesExtractor.extract(FILE_NAME);

            url = properties.getProperty(URL);
            dataBase = properties.getProperty(DATA_BASE);
            user = properties.getProperty(USER);
            password = properties.getProperty(PASSWORD);
        } catch (SQLException | ExtractException e) {
            throw new ConnectionException(e);
        }
    }

}
