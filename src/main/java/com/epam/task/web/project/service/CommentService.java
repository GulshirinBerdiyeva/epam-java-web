package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.CommentDao;
import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.PlaylistDao;
import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Playlist;

import java.util.List;

public class CommentService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public CommentService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Comment> getCommentsByMusicId(Long id) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDao commentDao = daoHelper.createCommentDao();

            return commentDao.findCommentByMusicId(id);

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

    public void save(Comment item) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            CommentDao commentDao = daoHelper.createCommentDao();

            commentDao.save(item);

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

}
