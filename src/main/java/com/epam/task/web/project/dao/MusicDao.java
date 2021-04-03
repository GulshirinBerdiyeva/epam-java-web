package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.mapper.MusicMapper;

import java.util.Optional;

public class MusicDao extends AbstractDao<Music>{

    private static final String TABLE_NAME = "music";
    private static final String FIND_BY_TITLE = "SELECT * FROM music WHERE title = ?";

    public MusicDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new MusicMapper(), TABLE_NAME);
    }

    public Optional<Music> findMusicByTitle(String title) throws DaoException {
        return executeForSingleResult(FIND_BY_TITLE, title);
    }

}
