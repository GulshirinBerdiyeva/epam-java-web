package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.UserDao;
import com.epam.task.web.project.entity.User;

import java.util.Optional;

public class UserService {

    private final DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao userDao = daoHelper.createUserDao();

            return userDao.findUserByLoginAndPassword(login, password);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}
