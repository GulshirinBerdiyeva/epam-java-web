package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchMusicCommand implements Command{

    private final MusicService musicService;

    private static final String ARTIST = "artist";
    private static final String TITLE = "title";
    private static final String SEARCHING_MUSICS = "searchingMusics";
    private static final String EMPTY_PARAMETERS = "emptyParameters";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String SHOW_SEARCHING_MUSICS = "showSearchingMusics";

    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    public SearchMusicCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String artist = request.getParameter(ARTIST);
        String title = request.getParameter(TITLE);

        if (artist.isEmpty() && title.isEmpty()) {
            request.setAttribute(EMPTY_PARAMETERS, true);
            return CommandResult.forward(SEARCH_PAGE);
        }

        List<Music> musics;
        if (!artist.isEmpty()) {
            musics = musicService.findMusicsBySearchParameter(ARTIST, artist);
        } else {
            musics = musicService.findMusicsBySearchParameter(TITLE, title);
        }

        if (musics.isEmpty()) {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(SEARCH_PAGE);
        }

        request.setAttribute(SHOW_SEARCHING_MUSICS, true);
        request.getSession(false).setAttribute(SEARCHING_MUSICS, musics);

        return CommandResult.forward(MAIN_PAGE);
    }

}
