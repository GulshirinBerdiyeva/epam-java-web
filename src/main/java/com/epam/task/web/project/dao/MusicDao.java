package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.mapper.MusicRowMapper;

public class MusicDao extends AbstractDao<Music>{

    private static final String TABLE_NAME = "music";

    public MusicDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new MusicRowMapper(), TABLE_NAME);
    }

}
