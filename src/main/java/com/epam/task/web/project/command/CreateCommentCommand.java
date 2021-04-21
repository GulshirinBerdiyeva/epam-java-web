package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.CommentService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateCommentCommand implements Command{

    private final CommentService commentService;
    private InputParameterValidator inputParameterValidator = new InputParameterValidator();

    private static final String USER = "user";
    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String NEW_COMMENT = "newComment";
    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";

    private static final String COMMENTS_COMMAND = "?command=comments";
    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";

    public CreateCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession().getAttribute(USER);
        Music music = (Music) request.getSession().getAttribute(SELECTED_MUSIC);
        String commentValue = request.getParameter(NEW_COMMENT);

        boolean isValid = inputParameterValidator.isValid(commentValue);
        if (isValid) {
            commentService.save(user, music, commentValue);
            return CommandResult.redirect(COMMENTS_COMMAND);
        } else {
            request.setAttribute(EMPTY_INPUT_PARAMETERS, true);
            return CommandResult.forward(PURCHASE_PAGE);
        }

    }

}
