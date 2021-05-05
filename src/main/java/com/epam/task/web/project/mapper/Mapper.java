package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Generic interface
 * Implements by definite Mapper classes
 *
 * @param <T> type of definite entity class which implements Entity
 * */
public interface Mapper<T extends Entity> {
    /**
     * Map data from database to entity
     *
     * @param resultSet resultSet by SQL query
     * @return definite entity type
     * @throws SQLException in case of incorrect type conversion or incorrect table column name
     * */
    T map(ResultSet resultSet) throws SQLException;
}
