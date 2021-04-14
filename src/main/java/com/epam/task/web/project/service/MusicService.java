package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class MusicService {

    private final DaoHelperFactory daoHelperFactory;

    private static final String ENCODING = "UTF-8";
    private static final String TITLE = "title";
    private static final String ARTIST = "artist";
    private static final String PRICE = "price";
    private static final String IMAGE_FILE = "imageFile";
    private static final String IMAGES_DIRECTORY = "D:/work/projects/WebAppOrderMusic/src/main/webapp/images";
    private static final String MUSICS_DIRECTORY = "D:/work/projects/WebAppOrderMusic/src/main/webapp/musics";
    private static final String IMAGES = "images/";
    private static final String MUSICS = "musics/";

    public MusicService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Music> getAllMusics() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Music> getMusicById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Music> findMusicByArtistAndTitle(String artist, String title) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.findMusicByArtistAndTitle(artist, title);
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void removeMusicById(List<User> allClients, Long musicId) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            MusicDao musicDao = daoHelper.createMusicDao();
            MusicOrderDao musicOrderDao = daoHelper.createMusicOrderDao();
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();
            AlbumDao albumDao = daoHelper.createAlbumDao();
            CommentDao commentDao = daoHelper.createCommentDao();

            daoHelper.startTransaction();

            for (User client : allClients) {
                Long clientId = client.getId();
                int musicAmount = client.getMusicAmount();
                boolean exist = playlistDao.isExist(clientId, musicId);
                if (exist) {
                    int newMusicAmount = musicAmount - 1;
                    userDao.updateMusicAmountById(clientId, newMusicAmount);
                }
            }

            musicOrderDao.removeById(musicId);
            playlistDao.removeById(musicId);
            albumDao.removeById(musicId);
            commentDao.removeById(musicId);
            musicDao.removeById(musicId);

            daoHelper.commit();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void updatePriceById(Long id, BigDecimal price) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            musicDao.updatePriceById(id, price);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void updateMusic(Music music) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            musicDao.updateMusic(music);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void saveMusic(Music music) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            musicDao.save(music);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Music extractData(List<FileItem> data) throws ServiceException {
        Music music = new Music();
        try {
            for(FileItem item : data){
                if(item.isFormField()){
                    String fieldName = item.getFieldName();
                    String value = item.getString(ENCODING);

                    switch (fieldName) {
                        case TITLE:
                            music.setTitle(value);
                            break;
                        case ARTIST:
                            music.setArtist(value);
                            break;
                        case PRICE:
                            music.setPrice(new BigDecimal(value));
                            break;
                        default:
                            throw new ServiceException("Unknown field type!");
                    }

                } else {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();

                    if (IMAGE_FILE.equals(fieldName)) {
                        music.setImagePath(IMAGES + fileName);
                        item.write( new File(IMAGES_DIRECTORY + File.separator + fileName));
                    } else {
                        music.setAudioPath(MUSICS + fileName);
                        item.write( new File(MUSICS_DIRECTORY + File.separator + fileName));
                    }
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return music;
    }

}

