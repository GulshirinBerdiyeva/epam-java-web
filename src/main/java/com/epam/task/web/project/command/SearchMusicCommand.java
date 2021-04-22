package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SearchMusicCommand implements Command{

    private final MusicService musicService;
    private InputParameterValidator inputParameterValidator = new InputParameterValidator();

    private static final String ARTIST = "artist";
    private static final String TITLE = "title";
    private static final String SEARCHING_MUSICS = "searchingMusics";
    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String SHOW_SEARCHING_MUSICS = "showSearchingMusics";

    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    public SearchMusicCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String artistValue = request.getParameter(ARTIST);
        String titleValue = request.getParameter(TITLE);

        boolean isValidArtistValue = inputParameterValidator.isValid(artistValue);
        boolean isValidTitleValue = inputParameterValidator.isValid(titleValue);
        if (!isValidArtistValue && !isValidTitleValue) {
            request.setAttribute(EMPTY_INPUT_PARAMETERS, true);
            return CommandResult.forward(SEARCH_PAGE);
        }

        List<Music> musics = new ArrayList<>();
        if (isValidArtistValue) {
            musics = musicService.findMusicsBySearchParameter(ARTIST, artistValue);
        }
        if (isValidTitleValue) {
            musics = musicService.findMusicsBySearchParameter(TITLE, titleValue);
        }

        if (musics.isEmpty()) {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(SEARCH_PAGE);

        } else {
            request.setAttribute(SHOW_SEARCHING_MUSICS, true);
            request.getSession().setAttribute(SEARCHING_MUSICS, musics);
            return CommandResult.forward(MAIN_PAGE);
        }

    }

}
