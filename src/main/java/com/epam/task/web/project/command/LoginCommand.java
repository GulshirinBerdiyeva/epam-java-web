package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command{

    private UserService userService;
    private MusicService musicService;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ADMIN = "admin";
    private static final String MUSICS = "musics";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String LOGIN_PAGE = "/index.jsp";

    public LoginCommand(ServiceFactory serviceFactory) {
        this.userService = (UserService) serviceFactory.create(USER);
        this.musicService = (MusicService) serviceFactory.create("music");
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        Optional<User> optionalUser = userService.login(username, password);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            request.getSession(true).setAttribute(USER, user);

            String role = String.valueOf(user.getRole()).toLowerCase();
            if (ADMIN.equals(role)) {
                request.getSession(true).setAttribute(ADMIN, ADMIN);
            }

            List<Music> musics = musicService.getMusics();
            request.getSession(true).setAttribute(MUSICS, musics);

            return CommandResult.forward(MAIN_PAGE);

        }else {
            request.setAttribute(ERROR_LOGIN, "Invalid username or password!");
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

}
