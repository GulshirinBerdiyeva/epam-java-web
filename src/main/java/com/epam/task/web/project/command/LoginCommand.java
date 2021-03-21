package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command{
    private UserService userService;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        Optional<User> optionalUser = userService.login(username, password);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            request.setAttribute(NAME, user.getName());

            return CommandResult.forward(MAIN_PAGE);
        }else {
            request.setAttribute(ERROR_LOGIN, "Invalid username or password!");
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

}
