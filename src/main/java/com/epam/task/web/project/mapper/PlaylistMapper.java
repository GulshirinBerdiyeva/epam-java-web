package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Entity;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistMapper implements Mapper{

    private final MusicMapper musicMapper = new MusicMapper();

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String MUSIC_ID = "music_id";

    @Override
    public Entity map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        Long musicId = resultSet.getLong(MUSIC_ID);
        Music music = musicMapper.map(resultSet);
        music.setId(musicId);

        return new Playlist(id, userId, musicId, music);
    }

}
