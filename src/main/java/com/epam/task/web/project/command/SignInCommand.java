package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SignInCommand implements Command {

    private final UserService userService;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String SIGN_UP = "signUp";
    private static final String ERROR_SIGN_IN = "errorSignIn";

    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String MAIN_COMMAND = "?command=main";

    public SignInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        boolean isExist = userService.isExist(username, password);

        if (!isExist) {
            Optional<User> optionalUser = userService.createNewUser(username, password);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession(true).setAttribute(USER, user);
                return CommandResult.redirect(request.getRequestURI() + MAIN_COMMAND);
            } else {
                request.setAttribute(SIGN_UP, true);
                request.setAttribute(ERROR_SIGN_IN, true);
                return CommandResult.forward(LOGIN_PAGE);
            }

        } else {
            request.setAttribute(SIGN_UP, true);
            request.setAttribute(ERROR_SIGN_IN, true);
            return CommandResult.forward(LOGIN_PAGE);
        }

    }

}
