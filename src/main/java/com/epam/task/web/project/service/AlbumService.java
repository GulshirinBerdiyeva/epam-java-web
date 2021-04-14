package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.entity.Music;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumService {

    private final DaoHelperFactory daoHelperFactory;

    public AlbumService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Album> getAllAlbums() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDao albumDao = daoHelper.createAlbumDao();

            return albumDao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Music> getAllMusicsByAlbumTitle(String albumTitle) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDao albumDao = daoHelper.createAlbumDao();

            List<Album> album = albumDao.getAllByAlbumTitle(albumTitle);
            List<Music> musics = new ArrayList<>();
            album.stream()
                    .forEach(albumMusic -> musics.add(albumMusic.getMusic()));

            return musics;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void saveAlbum(String albumTitle, String[] selectedMusicsId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()){
            AlbumDao albumDao = daoHelper.createAlbumDao();

            List<Long> musicsId = Arrays.stream(selectedMusicsId)
                                        .map(Long::parseLong)
                                        .collect(Collectors.toList());

            for (Long musicId : musicsId) {
                albumDao.save(new Album(musicId, albumTitle));
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public boolean isExist(String title) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDao albumDao = daoHelper.createAlbumDao();

            return albumDao.isExist(title);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void removeByTitle(String title) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            AlbumDao albumDao = daoHelper.createAlbumDao();

            albumDao.removeByTitle(title);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}
