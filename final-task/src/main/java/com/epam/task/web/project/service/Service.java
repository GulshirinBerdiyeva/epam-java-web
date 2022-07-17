package com.epam.task.web.project.service;

import com.epam.task.web.project.entity.Entity;

import java.util.List;
import java.util.Optional;

/**
 * Generic interface with general methods
 * Implements by AbstractService class
 *
 * @param <T> type of definite entity class which implements Entity
 * */
public interface Service<T extends Entity> {

    /**
     * Search data from database by entity id
     *
     * @param id entity id
     * @return   Optional<T> if entity find or Optional.empty()
     * @throws ServiceException if SQL query is invalid or some errors with database connection
     * */
    Optional<T> getById(Long id) throws ServiceException;

    /**
     * Extract all entity data
     *
     * @return   List<T>
     * @throws   ServiceException if SQL query is invalid or some errors with database connection
     * */
    List<T> getAll() throws ServiceException;

    /**
     * Save entity in database
     *
     * @param item entity to be saved
     * @throws   ServiceException if SQL query is invalid or some errors with database connection
     * */
    void save(T item) throws ServiceException;

}
