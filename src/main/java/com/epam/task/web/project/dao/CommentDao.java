package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.mapper.CommentMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommentDao extends AbstractDao<Comment> {

    private static final Logger LOGGER = LogManager.getLogger(CommentDao.class);

    private static final String SELECT_BY_MUSIC_ID = "SELECT * FROM comment " +
                                                     "INNER JOIN user ON comment.user_id = user.id " +
                                                     "WHERE music_id = ?";
    private static final String INSERT_COMMENT = "INSERT INTO comment (user_id, music_id, comment) VALUES (?, ?, ?)";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM comment WHERE music_id = ?";

    private static final AtomicReference<CommentDao> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private CommentDao() {
        super(new CommentMapper(), Comment.getTableName());
    }

    public static CommentDao getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    CommentDao commentDao = new CommentDao();

                    INSTANCE.set(commentDao);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created CommentDao instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    public List<Comment> getCommentByMusicId(Long id) throws DaoException {
        return executeQuery(SELECT_BY_MUSIC_ID, id);
    }

    @Override
    public void save(Comment item) throws DaoException {
        executeUpdate(INSERT_COMMENT, item.getUserId(), item.getMusicId(), item.getComment());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

}