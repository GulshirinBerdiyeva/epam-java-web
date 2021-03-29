package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface Dao <T extends Entity>{

    Optional<T> getById(Long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void save(T item) throws DaoException;

    void removeById(Long id) throws DaoException;

}
