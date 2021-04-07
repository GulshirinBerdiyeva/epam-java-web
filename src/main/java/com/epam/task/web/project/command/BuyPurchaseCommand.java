package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class BuyPurchaseCommand implements Command{

    private final PlaylistService playlistService;

    private  static final String USER = "user";
    private  static final String SELECTED_MUSIC = "selectedMusic";

    private  static final String EXIST_IN_PLAYLIST = "existInPlaylist";
    private  static final String NOT_ENOUGH_MONEY = "notEnoughMoney";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/fragments/purchase.jsp";
    private static final String MUSIC_ORDER_COMMAND = "?command=musicOrder";

    public BuyPurchaseCommand(ServiceFactory serviceFactory) {
        this.playlistService = (PlaylistService) serviceFactory.create(Playlist.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession(false).getAttribute(USER);
        Music music = (Music) request.getSession(false).getAttribute(SELECTED_MUSIC);

        if (!canBuy(request, user, music)) {
            return CommandResult.forward(PURCHASE_PAGE);
        }

        return CommandResult.redirect(request.getRequestURI() + MUSIC_ORDER_COMMAND);
    }

    private boolean canBuy(HttpServletRequest request, User user, Music music) throws ServiceException {

        Long userId = user.getId();
        Long musicId = music.getId();
        boolean isExistInPlaylist = playlistService.isExist(userId, musicId);

        if (isExistInPlaylist) {
            request.setAttribute(EXIST_IN_PLAYLIST, true);
            return false;
        }

        BigDecimal userCash = user.getCash();
        BigDecimal musicPrice = music.getPrice();

        if (musicPrice.compareTo(userCash) == 1) {
            request.setAttribute(NOT_ENOUGH_MONEY, true);
            return false;
        }

        return true;
    }

}
