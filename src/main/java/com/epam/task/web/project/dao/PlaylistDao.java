package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Playlist;

import com.epam.task.web.project.mapper.PlaylistMapper;

import java.util.List;

public class PlaylistDao extends AbstractDao<Playlist> {

    private static final String TABLE_NAME = "playlist";

    private static final String INSERT_PLAYLIST = "INSERT INTO playlist (user_id, music_id) VALUES (?, ?)";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from playlist WHERE user_id = ? AND music_id = ?)";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM playlist " +
                                                        "INNER JOIN music ON playlist.music_id = music.id " +
                                                            "WHERE user_id = ?";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM playlist WHERE music_id = ?";

    public PlaylistDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new PlaylistMapper(), TABLE_NAME);
    }

    public List<Playlist> getAllMusicsByUserId(Long userId) throws DaoException {
        return executeQuery(SELECT_BY_USER_ID, userId);
    }

    public boolean exist(Long userId, Long musicId) throws DaoException{
        return exist(SELECT_EXISTS, userId, musicId);
    }

    @Override
    public void save(Playlist item) throws DaoException {
        executeUpdate(INSERT_PLAYLIST, item.getUserId(), item.getMusicId());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

}
