package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Album;

import java.util.List;

public class AlbumService extends AbstractService<Album> {

    public AlbumService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, Album.getTableName());
    }

    public List<Album> getAllMusicsByAlbumTitle(String albumTitle) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            AlbumDao albumDao = (AlbumDao) daoHelper.createDao(getDaoType());

            return albumDao.getAllByAlbumTitle(albumTitle);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean exist(String title) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            AlbumDao albumDao = (AlbumDao) daoHelper.createDao(getDaoType());

            return albumDao.exist(title);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeByTitle(String title) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            AlbumDao albumDao = (AlbumDao) daoHelper.createDao(getDaoType());

            List<Album> albumMusics = albumDao.getAllByAlbumTitle(title);

            for (Album albumMusic : albumMusics) {
                Long musicId = albumMusic.getMusicID();
                albumDao.removeById(musicId);
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}