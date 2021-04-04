package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelperFactory;

public class ServiceFactory {

    private final DaoHelperFactory daoHelperFactory;

    public ServiceFactory(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Service create(String type) {
        switch (type) {
            case "user":
                return new UserService(daoHelperFactory);
            case "music":
                return new MusicService(daoHelperFactory);
            case "musicOrder":
                return new MusicOrderService(daoHelperFactory);
            case "playlist":
                return new PlaylistService(daoHelperFactory);
            case "albums":
                return new AlbumsService(daoHelperFactory);
            default:
                throw  new IllegalArgumentException("Unknown type of service!");
        }
    }

}
