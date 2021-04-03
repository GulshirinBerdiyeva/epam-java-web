package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.mapper.UserMapper;

import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final String TABLE_NAME = "user";
    private static final String FIND_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username = ? AND password = MD5(?)";
    private static final String UPDATE_USER_CASH_AND_MUSIC_AMOUNT = "UPDATE user SET cash = ? AND music_amount = ? WHERE id = ?";

    public UserDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new UserMapper(), TABLE_NAME);
    }

    public Optional<User> findUserByUsernameAndPassword(String username, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_USERNAME_AND_PASSWORD, username, password);
    }

    @Override
    public void save(User item) throws DaoException {
        User user = (User) item;
        executeUpdate(UPDATE_USER_CASH_AND_MUSIC_AMOUNT, user.getCash(), user.getMusicAmount(), user.getId());
    }

}
