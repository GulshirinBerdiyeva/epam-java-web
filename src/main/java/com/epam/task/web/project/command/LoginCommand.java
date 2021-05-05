package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command{

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ERROR_LOGIN = "errorLogin";

    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String MAIN_COMMAND = "?command=main";

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        Optional<User> optionalUser = userService.login(username, password);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            request.getSession(true).setAttribute(USER, user);

            return CommandResult.redirect(MAIN_COMMAND);
        } else {
            request.setAttribute(ERROR_LOGIN, true);
            return CommandResult.forward(LOGIN_PAGE);
        }

    }

}
