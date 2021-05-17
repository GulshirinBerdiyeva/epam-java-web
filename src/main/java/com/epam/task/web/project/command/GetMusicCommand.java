package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetMusicCommand implements Command {

    private static final String TYPE = "type";
    private static final String PAGE = "page";
    private static final String ALBUM = "album";
    private static final String MUSICS = "musics";
    private static final String MUSIC = "music";
    private static final String SEARCHING_MUSICS = "searchingMusics";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private final MusicService musicService;

    public GetMusicCommand(MusicService musicService) {
        this.musicService  = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String type = request.getParameter(TYPE);
        String pageValue = request.getParameter(PAGE);

        if (type == null || pageValue == null) {
            throw new NullPointerException("Parameter is NULL!");
        }

        List<Music> musics;
        switch (type) {
            case MUSICS:
                musics = (List<Music>) session.getAttribute(MUSICS);
                break;
            case ALBUM:
                musics = (List<Music>) session.getAttribute(ALBUM);
                break;
            case SEARCHING_MUSICS:
                musics = (List<Music>) session.getAttribute(SEARCHING_MUSICS);
                break;
            default:
                throw new IllegalArgumentException("Unknown type of musics description \"" + type + "\"");
        }

        int page = Integer.parseInt(pageValue);
        Music music = musics.get(page - 1);

        request.setAttribute(MUSIC, music);
        request.setAttribute(PAGE, page);

        return CommandResult.forward(MAIN_PAGE);
    }

}