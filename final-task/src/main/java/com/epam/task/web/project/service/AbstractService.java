package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Entity;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    private final DaoHelperFactory daoHelperFactory;
    private final String daoType;

    public AbstractService(DaoHelperFactory daoHelperFactory, String daoType) {
        this.daoHelperFactory = daoHelperFactory;
        this.daoType = daoType;
    }

    public DaoHelperFactory getDaoHelperFactory() {
        return daoHelperFactory;
    }

    public String getDaoType() {
        return daoType;
    }

    @Override
    public Optional<T> getById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            Dao<T> dao = daoHelper.createDao(daoType);

            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<T> getAll() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            Dao<T> dao = daoHelper.createDao(daoType);

            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(T item) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            Dao<T> dao = daoHelper.createDao(daoType);

            dao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
