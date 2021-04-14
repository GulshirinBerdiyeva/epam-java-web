package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.entity.Music;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumMapper implements Mapper<Album> {

    private final MusicMapper musicMapper = new MusicMapper();

    private static final String ID = "id";
    private static final String MUSIC_ID = "music_id";
    private static final String ALBUM_TITLE = "album_title";

    @Override
    public Album map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long musicId = resultSet.getLong(MUSIC_ID);
        String albumTitle = resultSet.getString(ALBUM_TITLE);
        Music music = musicMapper.map(resultSet);

        return new Album(id, musicId, albumTitle, music);
    }

}
