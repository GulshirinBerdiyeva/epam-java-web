package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.CommentService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCommentCommand implements Command{

    private final CommentService commentService;

    private static final String USER = "user";
    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String NEW_COMMENT = "newComment";

    private static final String COMMENTS_COMMAND = "?command=comments";

    public CreateCommentCommand(ServiceFactory serviceFactory) {
        this.commentService = (CommentService) serviceFactory.create(Comment.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession(false).getAttribute(USER);
        Music music = (Music) request.getSession(false).getAttribute(SELECTED_MUSIC);
        String newComment = request.getParameter(NEW_COMMENT);

        Long userId = user.getId();
        Long musicId = music.getId();

        Comment comment = new Comment(userId, musicId, newComment);

        commentService.save(comment);

        return CommandResult.redirect(request.getRequestURI() + COMMENTS_COMMAND);

    }

}
