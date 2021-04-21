package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.CommentDao;
import com.epam.task.web.project.dao.DaoException;
import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;

import java.sql.SQLException;
import java.util.List;

public class CommentService {

    private final DaoHelperFactory daoHelperFactory;

    public CommentService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Comment> findCommentsByMusicId(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDao commentDao = daoHelper.createCommentDao();

            return commentDao.findCommentByMusicId(id);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void save(User user, Music music, String newComment) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDao commentDao = daoHelper.createCommentDao();

            Comment comment = new Comment(user.getId(), music.getId(), newComment);
            commentDao.save(comment);
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }

}
