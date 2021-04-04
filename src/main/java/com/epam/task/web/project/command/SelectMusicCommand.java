package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.logic.CurrencyConverter;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

public class SelectMusicCommand implements Command{

    private final MusicService musicService;
    private static final String SERVICE_TYPE = "music";

    private static final String SELECTED_MUSIC_TITLE = "selectedMusicTitle";
    private static final String LOCAL = "local";
    private static final String SONG_IS_ABSENT = "songIsAbsent";

    private static final String SELECTED_MUSIC = "selectedMusic";

    private static final String SEARCH_PAGE = "/WEB-INF/view/fragments/search.jsp";

    public SelectMusicCommand(ServiceFactory serviceFactory) {
        this.musicService = (MusicService) serviceFactory.create(SERVICE_TYPE);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String selectedMusicTitle = request.getParameter(SELECTED_MUSIC_TITLE);
        String title = selectedMusicTitle.trim();

        Optional<Music> optionalMusic = musicService.findMusicByTitle(title);

        if (optionalMusic.isPresent()) {
            Music music = optionalMusic.get();

            convertPrice(request, music);

            request.setAttribute(SELECTED_MUSIC, music);
        } else {
            request.setAttribute(SONG_IS_ABSENT, true);
        }

        return CommandResult.forward(SEARCH_PAGE);

    }

    private void convertPrice(HttpServletRequest request, Music music) {
        HttpSession session = request.getSession(false);
        String localType = (String) session.getAttribute(LOCAL);

        if (localType == null) {
            localType = "EN";
        }

        BigDecimal price = music.getPrice();

        CurrencyConverter currencyConverter = new CurrencyConverter();
        BigDecimal result = currencyConverter.convert(localType, price);

        music.setPrice(result);
    }

}
