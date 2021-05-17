package com.epam.task.web.project.connection;

import com.mysql.cj.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
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
            return DriverManager.getConnection(url + dataBase, user, password);

        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    private void getProperties() {
        try {
            DriverManager.registerDriver(new Driver());

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty(URL);
            dataBase = properties.getProperty(DATA_BASE);
            user = properties.getProperty(USER);
            password = properties.getProperty(PASSWORD);

        } catch (SQLException | IOException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

}