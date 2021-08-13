package com.epam.task.web.project.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static final AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    private static final int CONNECTIONS_AMOUNT = 10;

    private static final Queue<ProxyConnection> allConnections = new ArrayDeque<>();
    private static final AtomicBoolean IS_EMPTY = new AtomicBoolean();

    private final Semaphore semaphore = new Semaphore(CONNECTIONS_AMOUNT);
    private final Lock connectionsLock = new ReentrantLock();

    private ConnectionPool() {
        List<ProxyConnection> connections = new ArrayList<>();

        for (int i = 0; i < CONNECTIONS_AMOUNT; i++) {
            Connection connection = connectionFactory.create();

            ProxyConnection proxyConnection = new ProxyConnection(connection, this);

            connections.add(proxyConnection);
        }

        allConnections.addAll(connections);
    }

    public static ConnectionPool getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    ConnectionPool connectionPool = new ConnectionPool();

                    INSTANCE.set(connectionPool);
                    IS_INSTANCE_CREATED.set(true);

                   LOGGER.info("Created ConnectionPool instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    public ProxyConnection getConnection() {
        try {
            semaphore.acquire();
            connectionsLock.lock();

            ProxyConnection proxyConnection = allConnections.remove();

            IS_EMPTY.set(allConnections.isEmpty());

            return proxyConnection;

        } catch (InterruptedException e) {
            LOGGER.fatal(e.getMessage(), e);
            throw new ConnectionException(e.getMessage(), e);

        } finally {
            connectionsLock.unlock();
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        try {
            connectionsLock.lock();

            allConnections.add(proxyConnection);

        } finally {
            connectionsLock.unlock();
            semaphore.release();
        }

    }

    public static AtomicBoolean getIsEmpty() {
        return IS_EMPTY;
    }

}
