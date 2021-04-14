package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.CommentService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommentsCommand implements Command{

    private final CommentService commentService;

    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String SELECTED_MUSIC_COMMENTS = "selectedMusicComments";
    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";

    public CommentsCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Music music = (Music) request.getSession(false).getAttribute(SELECTED_MUSIC);

        Long musicId = music.getId();
        List<Comment> comments = commentService.findCommentsByMusicId(musicId);

        request.getSession(false).setAttribute(SELECTED_MUSIC_COMMENTS, comments);
        return CommandResult.redirect(request.getRequestURI() + PURCHASE_PAGE_COMMAND);
    }

}
