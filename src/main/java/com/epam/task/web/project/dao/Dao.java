package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.Entity;

import java.util.List;
import java.util.Optional;

/**
 * Generic interface with general methods
 * Implements by AbstractDao class
 *
 * @param <T> type of definite entity class which implements Entity
 * */
public interface Dao <T extends Entity> {
    /**
     * Search data from database by entity id
     *
     * @param id entity id
     * @return   Optional<T> if entity find or Optional.empty()
     * @throws   DaoException if SQL query is invalid or some errors with database connection
     * */
    Optional<T> getById(Long id) throws DaoException;

    /**
     * Extract all entity data
     *
     * @return   List<T>
     * @throws   DaoException if SQL query is invalid or some errors with database connection
     * */
    List<T> getAll() throws DaoException;

    /**
     * Save entity in database
     *
     * @param item entity to be saved
     * @throws   DaoException if SQL query is invalid or some errors with database connection
     * */
    void save(T item) throws DaoException;

    /**
     * Remove data from database by entity id
     *
     * @param id entity id
     * @throws   DaoException if SQL query is invalid or some errors with database connection
     * */
    void removeById(Long id) throws DaoException;
}