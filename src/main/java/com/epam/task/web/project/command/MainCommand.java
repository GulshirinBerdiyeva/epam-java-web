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
    private static final String SERVICE_TYPE = "music";

    private static final String MUSICS = "musics";

    private static final String MAIN_PAGE_COMMAND = "?command=mainPage";

    public MainCommand(ServiceFactory serviceFactory) {
        this.musicService = (MusicService) serviceFactory.create(SERVICE_TYPE);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Music> musics = musicService.getAllMusics();
        request.getSession(true).setAttribute(MUSICS, musics);

        return CommandResult.redirect(request.getRequestURI() + MAIN_PAGE_COMMAND);
    }
}
