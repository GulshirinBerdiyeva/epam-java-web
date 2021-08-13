package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ConnectionPool;

public class DaoHelperFactory {

    private final ConnectionPool pool = ConnectionPool.getInstance();

    public DaoHelper create() {
        return new DaoHelper(pool.getConnection());
    }

}
