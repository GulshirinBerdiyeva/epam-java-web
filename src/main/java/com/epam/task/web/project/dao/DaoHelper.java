package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable{

    private final ProxyConnection proxyConnection;

    public DaoHelper(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public UserDao createUserDao() {
        return new UserDao(proxyConnection);
    }

    public MusicDao createMusicDao() {
        return new MusicDao(proxyConnection);
    }

    public void startTransaction() throws DaoException {
        try {
            proxyConnection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            proxyConnection.commit();
            proxyConnection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit() throws DaoException {
        try {
            proxyConnection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            proxyConnection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void close() throws Exception {
        proxyConnection.setAutoCommit(true);
        proxyConnection.close();
    }

}
