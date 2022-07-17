package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoException;
import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.PlaylistDao;
import com.epam.task.web.project.entity.Playlist;

import java.util.List;

public class PlaylistService extends AbstractService<Playlist> {

    public PlaylistService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, Playlist.getTableName());
    }

    public List<Playlist> getAllMusicsByUserId(Long userId) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            PlaylistDao playlistDao = (PlaylistDao) daoHelper.createDao(getDaoType());

            return playlistDao.getAllMusicsByUserId(userId);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean exist(Long userId, Long musicId) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            PlaylistDao playlistDao = (PlaylistDao) daoHelper.createDao(getDaoType());

            return playlistDao.exist(userId, musicId);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}