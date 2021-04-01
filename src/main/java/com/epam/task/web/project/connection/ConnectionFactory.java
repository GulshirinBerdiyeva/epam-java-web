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

    private static final String FILE_NAME = "connection.properties";

    private String url;
    private String data_base;
    private String user;
    private String password;

    private int connectionsAmount;
    private ConnectionPool pool;

    public ConnectionFactory (ConnectionPool pool, int connectionsAmount) {
        this.pool = pool;
        this.connectionsAmount = connectionsAmount;
    }

    public List<ProxyConnection> create() {
        try {
            getProperties();

            List<ProxyConnection> connections = new ArrayList<>();
            for (int i = 0; i < connectionsAmount; i++) {
                Connection connection = DriverManager.getConnection(url+data_base, user, password);

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

        url = properties.getProperty("url");
        data_base = properties.getProperty("dataBase");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }

}
