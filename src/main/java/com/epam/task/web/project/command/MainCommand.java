package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainCommand implements Command {

    private final MusicService musicService;

    private static final String MUSICS = "musics";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    public MainCommand(ServiceFactory serviceFactory) {
        this.musicService = (MusicService) serviceFactory.create(Music.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Music> musics = musicService.getAllMusics();
        request.getSession(true).setAttribute(MUSICS, musics);

        return CommandResult.forward(MAIN_PAGE);
    }
}
