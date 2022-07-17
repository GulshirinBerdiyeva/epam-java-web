package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.CommentDao;
import com.epam.task.web.project.dao.DaoException;
import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.entity.Comment;

import java.util.List;

public class CommentService extends AbstractService<Comment> {

    public CommentService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, Comment.getTableName());
    }

    public List<Comment> getCommentsByMusicId(Long id) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            CommentDao commentDao = (CommentDao) daoHelper.createDao(getDaoType());

            return commentDao.getCommentByMusicId(id);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}