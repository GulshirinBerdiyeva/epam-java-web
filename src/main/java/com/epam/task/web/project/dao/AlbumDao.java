package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.mapper.AlbumMapper;

import java.util.List;

public class AlbumDao extends AbstractDao<Album> {

    private static final String TABLE_NAME = "album";

    private static final String INSERT_INTO_ALBUM = "INSERT INTO album (music_id, album_title) VALUES (?, ?)";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from album WHERE album_title = ?)";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM album WHERE music_id = ?";
    private static final String REMOVE_SAFE_UPDATE = "SET SQL_SAFE_UPDATES = 0";
    private static final String REMOVE_BY_TITLE = "DELETE FROM album WHERE album_title = ?";
    private static final String SET_SAFE_UPDATE = "SET SQL_SAFE_UPDATES = 1";
    private static final String SELECT_BY_MUSIC_ID = "SELECT * FROM album " +
                                                            "INNER JOIN music ON album.music_id = music.id";
    private static final String SELECT_BY_ALBUM_TITLE = "SELECT * FROM album " +
                                                    "INNER JOIN music ON album.music_id = music.id " +
                                                        "WHERE album.album_title = ?";

    public AlbumDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new AlbumMapper(), TABLE_NAME);
    }

    @Override
    public List<Album> getAll() throws DaoException {
        return executeQuery(SELECT_BY_MUSIC_ID);
    }

    @Override
    public void save(Album item) throws DaoException {
        executeUpdate(INSERT_INTO_ALBUM, item.getMusicID(), item.getAlbum_title());
    }

    public boolean isExist(String title) throws DaoException{
        return isExistQuery(SELECT_EXISTS, title);
    }

    public List<Album> getAllByAlbumTitle(String albumTitle) throws DaoException {
        return executeQuery(SELECT_BY_ALBUM_TITLE, albumTitle);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

    public void removeByTitle(String title) throws DaoException {
        executeUpdate(REMOVE_SAFE_UPDATE);
        executeUpdate(REMOVE_BY_TITLE, title);
        executeUpdate(SET_SAFE_UPDATE);
    }

}
