package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class PurchaseCommand implements Command{

    private MusicService musicService;

    private static final String SEARCH_PAGE = "/WEB-INF/view/fragments/search.jsp";
    private static final String SELECTED_MUSIC = "selectedMusic";

    public PurchaseCommand(ServiceFactory serviceFactory) {
        this.musicService = (MusicService) serviceFactory.create("music");
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String musicTitle = request.getParameter(SELECTED_MUSIC);

        Optional<Music> optionalMusic = musicService.getSelectedMusic(musicTitle);

        if (optionalMusic.isPresent()) {
            Music music = optionalMusic.get();
            request.getSession(false).setAttribute(SELECTED_MUSIC, music);
        }

        return CommandResult.forward(SEARCH_PAGE);
    }
}
