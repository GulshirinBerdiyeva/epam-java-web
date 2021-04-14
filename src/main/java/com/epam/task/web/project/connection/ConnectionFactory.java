package com.epam.task.web.project.connection;

import com.mysql.cj.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionFactory {

    private String url;
    private String dataBase;
    private String user;
    private String password;
    private int connectionsAmount;
    private ConnectionPool pool;

    private static final String FILE_NAME = "connection.properties";
    private static final String URL = "url";
    private static final String DATA_BASE = "dataBase";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public ConnectionFactory (ConnectionPool pool, int connectionsAmount) {
        this.pool = pool;
        this.connectionsAmount = connectionsAmount;
    }

    public List<ProxyConnection> create() {
        try {
            getProperties();

            List<ProxyConnection> connections = new ArrayList<>();
            for (int i = 0; i < connectionsAmount; i++) {
                Connection connection = DriverManager.getConnection(url + dataBase, user, password);

                ProxyConnection proxyConnection = new ProxyConnection(connection, pool);

                connections.add(proxyConnection);
            }

            return connections;
        } catch (SQLException | IOException e) {
            throw new ConnectionException("Couldn't get access to the database!", e);
        }
    }

    private void getProperties() throws SQLException, IOException {
        DriverManager.registerDriver(new Driver());
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);

        Properties properties = new Properties();
        properties.load(inputStream);

        url = properties.getProperty(URL);
        dataBase = properties.getProperty(DATA_BASE);
        user = properties.getProperty(USER);
        password = properties.getProperty(PASSWORD);
    }

}
