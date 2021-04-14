package com.epam.task.web.project.connection;

import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private Queue<ProxyConnection> connectionsInUse = new ArrayDeque<>();

    private final Lock connectionsLock = new ReentrantLock();
    private static final int SEMAPHORE_SIZE = 10;
    private static final Semaphore SEMAPHORE = new Semaphore(SEMAPHORE_SIZE);

    private final ConnectionFactory connectionFactory = new ConnectionFactory(this, SEMAPHORE_SIZE);

    private ConnectionPool() {
        availableConnections.addAll(connectionFactory.create());
    }

    public static ConnectionPool getInstance() {
        if (!isInstanceCreated.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!isInstanceCreated.get()) {
                    ConnectionPool pool = new ConnectionPool();
                    INSTANCE.set(pool);
                    isInstanceCreated.set(true);

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
            SEMAPHORE.acquire();
            connectionsLock.lock();

            ProxyConnection proxyConnection = availableConnections.remove();
            connectionsInUse.add(proxyConnection);

            return proxyConnection;
        } catch (InterruptedException e) {
            throw new ConnectionException(e);
        } finally {
            connectionsLock.unlock();
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.add(proxyConnection);
            }
        } finally {
            connectionsLock.unlock();
            SEMAPHORE.release();
        }
    }

}
