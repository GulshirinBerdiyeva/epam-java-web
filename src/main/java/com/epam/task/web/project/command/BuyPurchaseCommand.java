package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.logic.CurrencyConverter;
import com.epam.task.web.project.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class BuyPurchaseCommand implements Command{

    private final PlaylistService playlistService;
    private final MusicOrderService musicOrderService;
    private final UserService userService;

    private  static final String USER = "user";
    private  static final String LOCAL = "local";
    private  static final String SELECTED_MUSIC = "selectedMusic";
    private  static final String EXIST_IN_PLAYLIST = "existInPlaylist";
    private  static final String NOT_ENOUGH_MONEY = "notEnoughMoney";
    private  static final String CAN_BUY = "canBuy";
    private  static final String MUSIC_ORDER = "musicOrder";
    private  static final String FINAL_PRICE = "finalPrice";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/fragments/purchase.jsp";

    public BuyPurchaseCommand(UserService userService,
                              MusicOrderService musicOrderService,
                              PlaylistService playlistService) {

        this.userService = userService;
        this.musicOrderService = musicOrderService;
        this.playlistService = playlistService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession(false).getAttribute(USER);
        Music music = (Music) request.getSession(false).getAttribute(SELECTED_MUSIC);

        boolean isExistInPlaylist = playlistService.isExist(user.getId(), music.getId());
        if (isExistInPlaylist) {
            request.setAttribute(EXIST_IN_PLAYLIST, true);
            return CommandResult.forward(PURCHASE_PAGE);
        }

        boolean isEnoughCash = userService.isEnoughCash(user, music);
        if (!isEnoughCash) {
            request.setAttribute(NOT_ENOUGH_MONEY, true);
            return CommandResult.forward(PURCHASE_PAGE);
        }

        MusicOrder musicOrder = musicOrderService.createOrder(user, music);

        String local = (String) request.getSession(false).getAttribute(LOCAL);
        CurrencyConverter currencyConverter = new CurrencyConverter();
        BigDecimal convertedPrice = currencyConverter.convertPrice(local, musicOrder.getFinalPrice());

        request.setAttribute(CAN_BUY, true);
        request.getSession(false).setAttribute(FINAL_PRICE, convertedPrice);
        request.getSession(false).setAttribute(MUSIC_ORDER, musicOrder);

        return CommandResult.forward(PURCHASE_PAGE);
    }

}

