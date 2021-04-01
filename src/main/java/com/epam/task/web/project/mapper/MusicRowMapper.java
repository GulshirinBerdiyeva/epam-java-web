package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Music;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicRowMapper implements RowMapper<Music>{

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String ARTIST = "artist";
    public static final String AUDIO_PATH = "audio_path";
    public static final String IMAGE_PATH = "image_path";

    @Override
    public Music map(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(ID);
        String title = resultSet.getString(TITLE);
        String artist = resultSet.getString(ARTIST);
        String audioPath = resultSet.getString(AUDIO_PATH);
        String imagePath = resultSet.getString(IMAGE_PATH);

        return new Music(id, title, artist, audioPath, imagePath);
    }

}
