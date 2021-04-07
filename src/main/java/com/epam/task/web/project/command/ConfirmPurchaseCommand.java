package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmPurchaseCommand implements Command{

    private final MusicOrderService musicOrderService;
    private final PlaylistService playlistService;

    private static final String MUSIC_ORDER = "musicOrder";
    private static final String USER = "user";
    private static final String PAYED = "payed";

    private  static final String EXIST_IN_PLAYLIST = "existInPlaylist";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/fragments/purchase.jsp";

    public ConfirmPurchaseCommand(ServiceFactory serviceFactory) {
        this.musicOrderService = (MusicOrderService) serviceFactory.create(MusicOrder.class);
        this.playlistService = (PlaylistService) serviceFactory.create(Playlist.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        MusicOrder musicOrder = (MusicOrder) request.getSession(false).getAttribute(MUSIC_ORDER);
        User user = (User) request.getSession(false).getAttribute(USER);

        Long userId = user.getId();
        Long musicId = musicOrder.getMusicId();

        boolean isExistInPlaylist = playlistService.isExist(userId, musicId);

        if (isExistInPlaylist) {
            request.setAttribute(EXIST_IN_PLAYLIST, true);

        } else {
            musicOrderService.confirmMusicOrder(musicOrder, user, musicId);

            request.getSession(false).setAttribute(USER, user);
            request.getSession(false).setAttribute(MUSIC_ORDER, musicOrder);
            request.setAttribute(PAYED, true);

        }

        return CommandResult.forward(PURCHASE_PAGE);
    }

}
