package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Playlist;

import com.epam.task.web.project.mapper.PlaylistMapper;

public class PlaylistDao extends AbstractDao<Playlist> {

    private static final String TABLE_NAME = "playlist";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from playlist WHERE user_id = ? AND music_id = ?)";


    public PlaylistDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new PlaylistMapper(), TABLE_NAME);
    }

    public boolean selectExistsInPlaylist(Long userId, Long musicId) throws DaoException{
        return existExecuteQuery(SELECT_EXISTS, userId, musicId);
    }
}
