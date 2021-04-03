package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.PlaylistDao;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;

public class PlaylistService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public PlaylistService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public boolean isExist(User user, Music music) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            Long clientID = user.getId();
            Long musicId = music.getId();

            boolean isExist = playlistDao.selectExistsInPlaylist(clientID, musicId);

            return isExist;

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }
}
