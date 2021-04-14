package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.mapper.UserMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User> {

    private static final String TABLE_NAME = "user";

    private static final String INSERT_USER = "INSERT INTO user (username, password, role, cash, music_amount, discount) " +
                                                    "VALUES (?, MD5(?), ?, ?, ?, ?)";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM user WHERE role = 'client'";
    private static final String FIND_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username = ? AND password = MD5(?)";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from user WHERE username = ? AND password = MD5(?))";
    private static final String UPDATE_CASH_AND_MUSIC_AMOUNT = "UPDATE user SET cash = ?, music_amount = ? WHERE id = ?";
    private static final String UPDATE_CASH = "UPDATE user SET cash = ? WHERE id = ?";
    private static final String UPDATE_DISCOUNT = "UPDATE user SET discount = ? WHERE id = ?";
    private static final String UPDATE_MUSIC_AMOUNT = "UPDATE user SET music_amount = ? WHERE id = ?";

    public UserDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new UserMapper(), TABLE_NAME);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_USERNAME_AND_PASSWORD, username, password);
    }

    public List<User> getAllClients() throws DaoException {
        return executeQuery(SELECT_ALL_CLIENTS);
    }

    public boolean isExist(String username, String password) throws DaoException {
        return isExistQuery(SELECT_EXISTS, username, password);
    }

    public void createNewUser(User user) throws DaoException {
        String role = String.valueOf(user.getRole());
        executeUpdate(INSERT_USER, user.getUsername(), user.getPassword(), role.toLowerCase(),
                      user.getCash(), user.getMusicAmount(), user.getDiscount());
    }

    public void updateCashAndMusicAmountById(User item) throws DaoException {
        executeUpdate(UPDATE_CASH_AND_MUSIC_AMOUNT, item.getCash(), item.getMusicAmount(), item.getId());
    }

    public void updateCashById(Long id, BigDecimal cash) throws DaoException {
        executeUpdate(UPDATE_CASH, cash, id);
    }

    public void updateDiscountById(Long id, int discount) throws DaoException {
        executeUpdate(UPDATE_DISCOUNT, discount, id);
    }

    public void updateMusicAmountById(Long id, int musicAmount) throws DaoException {
        executeUpdate(UPDATE_MUSIC_AMOUNT, musicAmount, id);
    }

}
