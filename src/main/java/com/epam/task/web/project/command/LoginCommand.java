package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command{

    private UserService userService;
    private MusicService musicService;

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String MUSICS = "musics";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String LOGIN_PAGE = "/index.jsp";

    public LoginCommand(UserService userService, MusicService musicService) {
        this.userService = userService;
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        Optional<User> optionalUser = userService.login(login, password);

        if (optionalUser.isPresent()){
            List<Music> musics = musicService.getMusics();
            request.getSession(true).setAttribute(MUSICS, musics);

            return CommandResult.forward(MAIN_PAGE);
        }else {
            request.setAttribute(ERROR_LOGIN, "Invalid username or password!");
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

}
