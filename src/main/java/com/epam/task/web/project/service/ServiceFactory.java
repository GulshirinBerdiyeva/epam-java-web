package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.entity.*;

public class ServiceFactory {

    private final DaoHelperFactory daoHelperFactory;

    public ServiceFactory(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public <T extends Entity> Service create(Class<T> classType) {

        if (classType == User.class) {
            return new UserService(daoHelperFactory);
        }
        if (classType == Music.class) {
            return new MusicService(daoHelperFactory);
        }
        if (classType == MusicOrder.class) {
            return new MusicOrderService(daoHelperFactory);
        }
        if (classType == Playlist.class) {
            return new PlaylistService(daoHelperFactory);
        }
        if (classType == Album.class) {
            return new AlbumService(daoHelperFactory);
        }
        if (classType == Comment.class) {
            return new CommentService(daoHelperFactory);
        }

        throw  new IllegalArgumentException("Unknown type of service!");

    }

}
