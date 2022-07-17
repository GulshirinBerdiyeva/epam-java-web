package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;
import java.util.*;

public class MusicService extends AbstractService<Music> {

    private static final String TITLE = "title";
    private static final String ARTIST = "artist";
    private static final String PRICE = "price";
    private static final String IMAGE_FILE = "imageFile";
    private static final String AUDIO_FILE = "audioFile";

    public MusicService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, Music.getTableName());
    }

    public Music createMusic(Map<String, String> musicValues) {
        Music music = new Music();

        music.setTitle(musicValues.get(TITLE));
        music.setArtist(musicValues.get(ARTIST));
        music.setPrice(new BigDecimal(musicValues.get(PRICE)));
        music.setImageFileName(musicValues.get(IMAGE_FILE));
        music.setAudioFileName(musicValues.get(AUDIO_FILE));

        return music;
    }

    public Optional<Music> getMusicByArtistAndTitle(String artist, String title) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            MusicDao musicDao = (MusicDao) daoHelper.createDao(getDaoType());

            return musicDao.getMusicByArtistAndTitle(artist, title);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Music> getMusicsBySearchParameter(String searchParameter, String value) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            MusicDao musicDao = (MusicDao) daoHelper.createDao(getDaoType());

            if (ARTIST.equals(searchParameter)) {
                return musicDao.getMusicsByArtist(value);
            } else {
                return musicDao.getMusicsByTitle(value);
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updatePriceById(Long id, BigDecimal price) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            MusicDao musicDao = (MusicDao) daoHelper.createDao(getDaoType());

            musicDao.updatePriceById(id, price);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateMusic(Music music) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            MusicDao musicDao = (MusicDao) daoHelper.createDao(getDaoType());

            musicDao.updateMusic(music);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeMusicById(List<User> allClients, Long musicId) throws ServiceException {
        DaoHelper daoHelper = getDaoHelperFactory().create();

        try {
            UserDao userDao = (UserDao) daoHelper.createDao(User.getTableName());
            MusicDao musicDao = (MusicDao) daoHelper.createDao(getDaoType());
            PlaylistDao playlistDao = (PlaylistDao) daoHelper.createDao(Playlist.getTableName());

            daoHelper.startTransaction();

            for (User client : allClients) {
                Long clientId = client.getId();
                int musicAmount = client.getMusicAmount();

                boolean exist = playlistDao.exist(clientId, musicId);

                if (exist) {
                    int newMusicAmount = musicAmount - 1;
                    client.setMusicAmount(newMusicAmount);
                    userDao.updateMusicAmountById(clientId, newMusicAmount);
                }
            }

            musicDao.removeById(musicId);

            daoHelper.endTransaction();

        } catch (DaoException e) {

            try {
                daoHelper.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException(daoException);
            }

            throw new ServiceException(e);
        }
    }

}