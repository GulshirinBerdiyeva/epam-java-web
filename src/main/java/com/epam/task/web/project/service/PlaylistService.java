package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoException;
import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.PlaylistDao;
import com.epam.task.web.project.entity.Playlist;

import java.util.List;

public class PlaylistService {

    private final DaoHelperFactory daoHelperFactory;

    public PlaylistService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Playlist> getAllMusicsByUserId(Long userId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            return playlistDao.getAllMusicsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean exist(Long userId, Long musicId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            return playlistDao.exist(userId, musicId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Playlist item) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            playlistDao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}