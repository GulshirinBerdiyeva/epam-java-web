package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Playlist;

import com.epam.task.web.project.mapper.PlaylistMapper;

public class PlaylistDao extends AbstractDao<Playlist> {

    private static final String TABLE_NAME = "playlist";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from playlist WHERE user_id = ? AND music_id = ?)";
    private static final String INSERT_PLAYLIST = "INSERT INTO playlist (user_id, music_id) VALUES (?, ?)";

    public PlaylistDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new PlaylistMapper(), TABLE_NAME);
    }

    public boolean isExistQuery(Long userId, Long musicId) throws DaoException{
        return isExistQuery(SELECT_EXISTS, userId, musicId);
    }

    @Override
    public void save(Playlist item) throws DaoException {
        executeUpdate(INSERT_PLAYLIST, item.getUserId(), item.getMusicId());
    }

}
