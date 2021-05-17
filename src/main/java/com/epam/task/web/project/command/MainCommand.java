package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainCommand implements Command {

    private static final String MUSICS = "musics";
    private static final String SIZE = "size";
    private static final String MUSIC = "music";
    private static final String SHOW_MUSICS = "showMusics";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private final MusicService musicService;

    public MainCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        List<Music> musics = musicService.getAllMusics();

        session.setAttribute(MUSICS, musics);
        session.setAttribute(SIZE, musics.size());
        request.setAttribute(SHOW_MUSICS, true);
        request.setAttribute(MUSIC, musics.get(0));

        return CommandResult.forward(MAIN_PAGE);
    }

}