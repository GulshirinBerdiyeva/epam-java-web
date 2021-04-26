package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

public class MusicService {

    private final DaoHelperFactory daoHelperFactory;

    private static final String TITLE = "title";
    private static final String ARTIST = "artist";
    private static final String PRICE = "price";
    private static final String IMAGE_FILE = "imageFile";
    private static final String AUDIO_FILE = "audioFile";
    private static final String FILE_NAME = "appResources.properties";
    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";
    private static final String UTF_8 = "UTF-8";


    public MusicService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Music> getAllMusics() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.getAll();
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Music> getMusicById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.getById(id);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Music> findMusicByArtistAndTitle(String artist, String title) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.findMusicByArtistAndTitle(artist, title);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Music> findMusicsBySearchParameter(String searchParameter, String value) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            if (ARTIST.equals(searchParameter)) {
                return musicDao.findMusicsByArtist(value);
            } else {
                return musicDao.findMusicsByTitle(value);
            }
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeMusicById(List<User> allClients, Long musicId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            MusicDao musicDao = daoHelper.createMusicDao();
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            daoHelper.startTransaction();

            for (User client : allClients) {
                Long clientId = client.getId();
                int musicAmount = client.getMusicAmount();
                boolean exist = playlistDao.exist(clientId, musicId);
                if (exist) {
                    int newMusicAmount = musicAmount - 1;
                    userDao.updateMusicAmountById(clientId, newMusicAmount);
                }
            }
            musicDao.removeById(musicId);

            daoHelper.endTransaction();

        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updatePriceById(Long id, BigDecimal price) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            musicDao.updatePriceById(id, price);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateMusic(Music music) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            musicDao.updateMusic(music);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void saveMusic(Music music) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            musicDao.save(music);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void saveFiles(List<FileItem> musicData) throws ServiceException {
        try {
            for (FileItem item : musicData) {
                if (!item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String directoryName;
                    String fileName = item.getName();

                    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
                    Properties properties = new Properties();
                    properties.load(inputStream);

                    if (IMAGE_FILE.equals(fieldName)) {
                        directoryName = properties.getProperty(IMAGES_DIRECTORY);
                    } else {
                        directoryName = properties.getProperty(MUSICS_DIRECTORY);
                    }

                    item.write(new File(directoryName + File.separator + fileName));
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Map<String, String> extractInputParameters(List<FileItem> musicData) throws ServiceException{
        Map<String, String> inputParameters = new HashMap<>();
        try {
            for (FileItem item : musicData) {
                String fieldName = item.getFieldName();
                String fieldValue;

                if (item.isFormField()) {
                    fieldValue = item.getString(UTF_8);
                } else {
                    fieldValue = item.getName();
                }

                inputParameters.put(fieldName, fieldValue);
            }
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(e);
        }

        return inputParameters;
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

}

