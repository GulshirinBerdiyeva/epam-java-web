package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelperFactory;

public class AlbumService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public AlbumService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
}
