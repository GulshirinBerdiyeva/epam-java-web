package com.epam.task.web.project.controller;

import com.epam.task.web.project.connection.ConnectionException;
import com.epam.task.web.project.connection.ConnectionPool;
import com.epam.task.web.project.connection.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ConnectionCloser {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionCloser.class);

    private final ConnectionPool pool = ConnectionPool.getInstance();
    private final Lock connectionsLock = new ReentrantLock();

    public void closeAllConnections() throws ConnectionException {
        try {
            connectionsLock.lock();

            if (pool != null) {

                while (!ConnectionPool.getIsEmpty().get()) {
                    ProxyConnection proxyConnection = pool.getConnection();

                    proxyConnection.close(true);
                }

                LOGGER.info("All connections closed");
            }

        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);

        } finally {
            connectionsLock.unlock();
        }
    }

}
