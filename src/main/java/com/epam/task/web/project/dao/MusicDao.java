package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.mapper.MusicMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class MusicDao extends AbstractDao<Music>{

    private static final String TABLE_NAME = "music";

    private static final String INSERT_MUSIC = "INSERT INTO music (title, artist, audio_path, image_path, price)" +
                                                    "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ARTIST = "SELECT * FROM music WHERE artist = ?";
    private static final String SELECT_BY_TITLE = "SELECT * FROM music WHERE title = ?";
    private static final String SELECT_BY_ARTIST_AND_TITLE = "SELECT * FROM music WHERE artist = ? AND title = ?";
    private static final String UPDATE_MUSIC = "UPDATE music SET title = ?, artist = ?, " +
                                                    "audio_path = ?, image_path = ?, price = ? WHERE id = ?";
    private static final String UPDATE_PRICE = "UPDATE music SET price = ? WHERE id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM music WHERE id = ?";

    public MusicDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new MusicMapper(), TABLE_NAME);
    }

    public void updateMusic(Music music) throws DaoException {
        executeUpdate(UPDATE_MUSIC, music.getTitle(), music.getArtist(),
                music.getAudioPath(), music.getImagePath(), music.getPrice(), music.getId());
    }

    @Override
    public void save(Music item) throws DaoException {
        executeUpdate(INSERT_MUSIC, item.getTitle(), item.getArtist(),
                item.getAudioPath(), item.getImagePath(), item.getPrice());
    }

    public Optional<Music> findMusicByArtistAndTitle(String artist, String title) throws DaoException {
        return executeForSingleResult(SELECT_BY_ARTIST_AND_TITLE, artist, title);
    }

    public List<Music> findMusicsByArtist(String artist) throws DaoException {
        return executeQuery(SELECT_BY_ARTIST, artist);
    }

    public List<Music> findMusicsByTitle(String title) throws DaoException {
        return executeQuery(SELECT_BY_TITLE, title);
    }

    public void updatePriceById(Long id, BigDecimal price) throws DaoException {
        executeUpdate(UPDATE_PRICE, price, id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

}
