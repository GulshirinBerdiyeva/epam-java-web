package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelperFactory;

public class AlbumsService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public AlbumsService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
}
