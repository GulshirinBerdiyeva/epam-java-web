package com.epam.task.web.project.command;

import com.epam.task.web.project.dao.DaoException;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.MusicOrderService;
import com.epam.task.web.project.service.PlaylistService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyCommand implements Command{

    private MusicOrderService musicOrderService;
    private PlaylistService playlistService;

    private static final String SEARCH_PAGE = "/WEB-INF/view/fragments/search.jsp";

    public BuyCommand(ServiceFactory serviceFactory) {

        this.musicOrderService = (MusicOrderService) serviceFactory.create("musicOrder");
        this.playlistService = (PlaylistService) serviceFactory.create("playlist");
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession(false).setAttribute("buy", true);
        User user = (User) request.getSession(false).getAttribute("user");
        Music music = (Music) request.getSession(false).getAttribute("selectedMusic");

        try {
            boolean isExistInPlaylist = playlistService.isExist(user, music);

            if (!isExistInPlaylist) {
                musicOrderService.buy(user, music);
            }

        } catch (DaoException e) {
            throw  new ServiceException(e);
        }

        return CommandResult.forward(SEARCH_PAGE);
    }
}
