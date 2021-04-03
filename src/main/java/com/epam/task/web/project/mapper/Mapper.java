package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T extends Entity> {
    T map(ResultSet resultSet) throws SQLException;
}
