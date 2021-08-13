package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoException;
import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.UserDao;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class UserService extends AbstractService<User> {

    public UserService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, User.getTableName());
    }

    public Optional<User> login(String username, String password) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            UserDao userDao = (UserDao) daoHelper.createDao(getDaoType());

            return userDao.getByUsernameAndPassword(username, password);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean exist(String username, String password) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            UserDao userDao = (UserDao) daoHelper.createDao(getDaoType());

            return userDao.exist(username, password);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> createNewUser(String username, String password) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            UserDao userDao = (UserDao) daoHelper.createDao(getDaoType());

            User user = User.createClient(username, password, new BigDecimal("0"), 0, 0);

            userDao.save(user);

            return userDao.getByUsernameAndPassword(username, password);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean isEnoughCash(User user, Music music) {
        BigDecimal userCash = user.getCash();
        BigDecimal musicPrice = music.getPrice();

        return musicPrice.compareTo(userCash) != 1;
    }

    public void updateBalance(User user, BigDecimal cash) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            UserDao userDao = (UserDao) daoHelper.createDao(getDaoType());

            BigDecimal currentCash = user.getCash();

            BigDecimal newCash = currentCash.add(cash).setScale(2, RoundingMode.HALF_UP);

            userDao.updateCashById(user.getId(), newCash);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateDiscount(Long id, int discount) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            UserDao userDao = (UserDao) daoHelper.createDao(getDaoType());

            userDao.updateDiscountById(id, discount);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAllClients() throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            UserDao userDao = (UserDao) daoHelper.createDao(getDaoType());

            return userDao.getAllClients();

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}