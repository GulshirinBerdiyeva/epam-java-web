package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.Role;
import com.epam.task.web.project.entity.User;
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
    private static final String USER = "user";
    private static final String SONG_IS_ABSENT = "songIsAbsent";

    private static final String SELECTED_MUSIC = "selectedMusic";

    private static final String SEARCH_PAGE = "/WEB-INF/view/fragments/search.jsp";
    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";
    private static final String EDIT_PAGE_COMMAND = "?command=editPage";

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

            User user = (User) request.getSession(false).getAttribute(USER);
            Role userRole = user.getRole();

            return (Role.ADMIN == userRole) ?
                    CommandResult.redirect(request.getRequestURI() + EDIT_PAGE_COMMAND) :
                        CommandResult.redirect(request.getRequestURI() + PURCHASE_PAGE_COMMAND);

        } else {
            request.setAttribute(SONG_IS_ABSENT, true);
            return CommandResult.forward(SEARCH_PAGE);

        }

    }


}
