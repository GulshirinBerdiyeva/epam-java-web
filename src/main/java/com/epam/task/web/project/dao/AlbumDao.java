package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.mapper.AlbumMapper;

import java.util.List;

public class AlbumDao extends AbstractDao<Album> {

    private static final String TABLE_NAME = "album";

    private static final String SELECT_BY_ALBUM_TITLE = "SELECT * FROM album INNER JOIN music ON album.music_id = music.id WHERE album.album_title = ?";
    private static final String SELECT_BY_MUSIC_ID = "SELECT * FROM album INNER JOIN music ON album.music_id = music.id";
    private static final String INSERT_INTO_ALBUM = "INSERT INTO album (music_id, album_title) VALUES (?, ?)";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from album WHERE album_title = ?)";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM album WHERE music_id = ?";

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

    public boolean exist(String title) throws DaoException{
        return exist(SELECT_EXISTS, title);
    }

    public List<Album> getAllByAlbumTitle(String albumTitle) throws DaoException {
        return executeQuery(SELECT_BY_ALBUM_TITLE, albumTitle);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

}
