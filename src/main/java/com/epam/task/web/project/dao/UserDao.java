package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserDao extends AbstractDao<User> {

    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);

    private static final String INSERT_USER = "INSERT INTO user " +
                                              "(username, password, role, cash, music_amount, discount) " +
                                              "VALUES (?, MD5(?), ?, ?, ?, ?)";
    private static final String GET_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username = ? AND password = MD5(?)";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from user WHERE username = ? AND password = MD5(?))";
    private static final String UPDATE_CASH_AND_MUSIC_AMOUNT = "UPDATE user SET cash = ?, music_amount = ? WHERE id = ?";
    private static final String UPDATE_MUSIC_AMOUNT = "UPDATE user SET music_amount = ? WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS = "SELECT * FROM user WHERE role = 'client'";
    private static final String UPDATE_DISCOUNT = "UPDATE user SET discount = ? WHERE id = ?";
    private static final String UPDATE_CASH = "UPDATE user SET cash = ? WHERE id = ?";

    private static final AtomicReference<UserDao> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private UserDao() {
        super(new UserMapper(), User.getTableName());
    }

    public static UserDao getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    UserDao userDao = new UserDao();

                    INSTANCE.set(userDao);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created UserDao instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    public Optional<User> getByUsernameAndPassword(String username, String password) throws DaoException {
        return executeForSingleResult(GET_BY_USERNAME_AND_PASSWORD, username, password);
    }

    public List<User> getAllClients() throws DaoException {
        return executeQuery(SELECT_ALL_CLIENTS);
    }

    public boolean exist(String username, String password) throws DaoException {
        return exist(SELECT_EXISTS, username, password);
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

    @Override
    public void save(User item) throws DaoException {
        String role = String.valueOf(item.getRole());
        executeUpdate(INSERT_USER, item.getUsername(), item.getPassword(),
                      role.toLowerCase(), item.getCash(), item.getMusicAmount(), item.getDiscount());
    }

}