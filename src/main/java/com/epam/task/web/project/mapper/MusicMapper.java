package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Music;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MusicMapper implements Mapper<Music> {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String ARTIST = "artist";
    public static final String AUDIO_FILE_NAME = "audio_file_name";
    public static final String IMAGE_FILE_NAME = "image_file_name";
    public static final String PRICE = "price";

    @Override
    public Music map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String title = resultSet.getString(TITLE);
        String artist = resultSet.getString(ARTIST);
        String audioFileName = resultSet.getString(AUDIO_FILE_NAME);
        String imageFileName = resultSet.getString(IMAGE_FILE_NAME);
        BigDecimal price = resultSet.getBigDecimal(PRICE);

        return new Music(id, title, artist, audioFileName, imageFileName, price);
    }

}
