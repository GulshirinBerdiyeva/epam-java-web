package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.PlaylistDao;

public class PlaylistService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public PlaylistService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public boolean isExist(Long userId, Long musicId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            return playlistDao.isExistQuery(userId, musicId);

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

}
