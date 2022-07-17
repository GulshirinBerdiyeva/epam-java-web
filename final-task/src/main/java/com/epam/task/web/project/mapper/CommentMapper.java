package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CommentMapper implements Mapper<Comment>{

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String USERNAME = "username";
    private static final String MUSIC_ID = "music_id";
    public static final String DATE = "date";
    public static final String COMMENT = "comment";

    @Override
    public Comment map(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        String username = resultSet.getString(USERNAME);
        Long musicId = resultSet.getLong(MUSIC_ID);
        LocalDateTime date = resultSet.getTimestamp(DATE).toLocalDateTime();
        String comment = resultSet.getString(COMMENT);

        return new Comment(id, userId, username, musicId, date, comment);
    }

}
