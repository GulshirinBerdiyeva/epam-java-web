package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.logic.CurrencyConverter;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Optional;

public class SelectMusicCommand implements Command{

    private final MusicService musicService;

    private static final String SELECTED_MUSIC_ID = "selectedMusicId";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String LOCAL = "local";
    private static final String SELECTED_MUSIC_PRICE = "selectedMusicPrice";

    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";
    private static final String COMMENTS_COMMAND = "?command=comments";

    public SelectMusicCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long id = Long.valueOf(request.getParameter(SELECTED_MUSIC_ID));

        Optional<Music> optionalMusic = musicService.getMusicById(id);

        if (optionalMusic.isPresent()) {
            Music music = optionalMusic.get();
            request.getSession().setAttribute(SELECTED_MUSIC, music);

            String local = (String) request.getSession().getAttribute(LOCAL);
            CurrencyConverter currencyConverter = new CurrencyConverter();
            BigDecimal convertedPrice = currencyConverter.convertPrice(local, music.getPrice());

            request.getSession().setAttribute(SELECTED_MUSIC_PRICE, convertedPrice);
            return CommandResult.redirect(COMMENTS_COMMAND);
        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(SEARCH_PAGE);
        }

    }

}
