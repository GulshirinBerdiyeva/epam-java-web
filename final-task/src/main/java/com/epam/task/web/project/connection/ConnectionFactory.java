package com.epam.task.web.project.connection;

import com.epam.task.web.project.data.DataException;
import com.epam.task.web.project.data.PropertiesInitializer;
import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);

    private static final String FILE_NAME = "connection.properties";
    private static final String URL = "url";
    private static final String DATA_BASE = "dataBase";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private String url;
    private String dataBase;
    private String user;
    private String password;

    private final PropertiesInitializer propertiesInitializer = new PropertiesInitializer();

    public ConnectionFactory() {
        getProperties();
    }

    public Connection create() throws ConnectionException {
        try {
            return DriverManager.getConnection(url + dataBase, user, password);

        } catch (SQLException e) {
            LOGGER.error("Failed to connect to database");
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    private void getProperties() {
        try {
            DriverManager.registerDriver(new Driver());

            Properties properties = propertiesInitializer.init(FILE_NAME);

            url = properties.getProperty(URL);
            dataBase = properties.getProperty(DATA_BASE);
            user = properties.getProperty(USER);
            password = properties.getProperty(PASSWORD);

        } catch (SQLException | DataException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

}
