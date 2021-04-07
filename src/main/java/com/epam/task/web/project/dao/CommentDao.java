package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.mapper.CommentMapper;

import java.util.List;

public class CommentDao extends AbstractDao<Comment> {

    private static final String TABLE_NAME = "comment";

    private static final String FIND_BY_MUSIC_ID = "SELECT * " +
                                                    "FROM comment INNER JOIN user " +
                                                    "ON comment.user_id = user.id " +
                                                    "WHERE music_id = ?";
    private static final String INSERT_COMMENT = "INSERT INTO comment (user_id, music_id, comment) VALUES (?, ?, ?)";

    public CommentDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new CommentMapper(), TABLE_NAME);
    }

    public List<Comment> findCommentByMusicId(Long id) throws DaoException {
        return executeQuery(FIND_BY_MUSIC_ID, id);
    }

    @Override
    public void save(Comment item) throws DaoException {
        executeUpdate(INSERT_COMMENT, item.getUserId(), item.getMusicId(), item.getComment());
    }

}
