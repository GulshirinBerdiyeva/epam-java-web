package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.logic.CurrencyConverter;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SelectMusicCommand implements Command{

    private final MusicService musicService;

    private static final String SELECTED_MUSIC_TITLE = "selectedMusicTitle";
    private static final String SONG_IS_ABSENT = "songIsAbsent";

    private static final String SELECTED_MUSIC = "selectedMusic";

    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";
    private static final String COMMENTS_COMMAND = "?command=comments";

    public SelectMusicCommand(ServiceFactory serviceFactory) {
        this.musicService = (MusicService) serviceFactory.create(Music.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String selectedMusicTitle = request.getParameter(SELECTED_MUSIC_TITLE);
        String title = selectedMusicTitle.trim();

        Optional<Music> optionalMusic = musicService.findMusicByTitle(title);

        if (optionalMusic.isPresent()) {
            Music music = optionalMusic.get();

            CurrencyConverter currencyConverter = new CurrencyConverter();
            currencyConverter.convertPrice(request, music.getPrice());

            request.getSession(false).setAttribute(SELECTED_MUSIC, music);

            return CommandResult.redirect(request.getRequestURI() + COMMENTS_COMMAND);

        } else {
            request.setAttribute(SONG_IS_ABSENT, true);
            return CommandResult.forward(SEARCH_PAGE);

        }

    }

}
