package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.CommentDao;
import com.epam.task.web.project.dao.DaoException;
import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.entity.Comment;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class CommentServiceTest {

    private static final String COMMENT_VALUE = "Top music!";
    private static final Long MUSIC_ID = 42L;
    private static final Comment FIRST_COMMENT = new Comment(1L, MUSIC_ID, COMMENT_VALUE);
    private static final Comment SECOND_COMMENT = new Comment(2L, MUSIC_ID, COMMENT_VALUE);
    private static final List<Comment> COMMENTS = Arrays.asList(FIRST_COMMENT, SECOND_COMMENT);

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final CommentService commentService = new CommentService(daoHelperFactory);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final CommentDao commentDao = Mockito.mock(CommentDao.class);

    @Test
    public void findCommentsByMusicIdShouldReturnListWhenMusicIdApplied() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createCommentDao()).thenReturn(commentDao);
        when(commentDao.findCommentByMusicId(MUSIC_ID)).thenReturn(COMMENTS);

        //when
        List<Comment> actual = commentService.findCommentsByMusicId(MUSIC_ID);

        //then
        Assert.assertFalse(actual.isEmpty());
    }

    @Test(expected = ServiceException.class)
    public void findCommentsByMusicIdShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createCommentDao()).thenReturn(commentDao);
        when(commentDao.findCommentByMusicId(MUSIC_ID)).thenThrow(new DaoException());

        //when
        List<Comment> actual = commentService.findCommentsByMusicId(MUSIC_ID);
    }

}
