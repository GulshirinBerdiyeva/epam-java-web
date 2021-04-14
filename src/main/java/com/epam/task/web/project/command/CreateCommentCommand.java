package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.CommentService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCommentCommand implements Command{

    private final CommentService commentService;

    private static final String USER = "user";
    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String NEW_COMMENT = "newComment";

    private static final String COMMENTS_COMMAND = "?command=comments";

    public CreateCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute(USER);
        Music music = (Music) request.getSession(false).getAttribute(SELECTED_MUSIC);
        String newComment = request.getParameter(NEW_COMMENT);

        commentService.save(user, music, newComment);

        return CommandResult.redirect(request.getRequestURI() + COMMENTS_COMMAND);
    }

}
