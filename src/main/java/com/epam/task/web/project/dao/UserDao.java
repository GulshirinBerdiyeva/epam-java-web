package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.mapper.UserRowMapper;

import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final String TABLE_NAME = "user";
    private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = MD5(?)";

    public UserDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new UserRowMapper(), TABLE_NAME);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, login, password);
    }

}
