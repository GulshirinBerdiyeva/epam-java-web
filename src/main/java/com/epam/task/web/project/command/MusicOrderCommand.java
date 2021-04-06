package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MusicOrderCommand implements Command{

    private  static final String USER = "user";
    private  static final String SELECTED_MUSIC = "selectedMusic";

    private  static final String CAN_BUY = "canBuy";
    private  static final String MUSIC_ORDER = "musicOrder";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/fragments/purchase.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        User user = (User) request.getSession(false).getAttribute(USER);
        Music music = (Music) request.getSession(false).getAttribute(SELECTED_MUSIC);

        int musicAmount = user.getMusicAmount();
        int discount = getDiscount(musicAmount);

        BigDecimal musicPrice = music.getPrice();
        BigDecimal finalPrice = getFinalPrice(discount, musicPrice);

        MusicOrder musicOrder = new MusicOrder(music.getId(), user.getId(), discount, finalPrice, false);

        request.setAttribute(CAN_BUY, true);
        request.getSession(false).setAttribute(MUSIC_ORDER, musicOrder);

        return CommandResult.forward(PURCHASE_PAGE);
    }

    private BigDecimal getFinalPrice(int discount, BigDecimal musicPrice) {
        BigDecimal percentageAmount = BigDecimal.valueOf(discount).multiply
                                    (musicPrice.divide(BigDecimal.valueOf(100)));

        return musicPrice.subtract(percentageAmount).setScale(2, RoundingMode.HALF_UP);
    }

    private int getDiscount(int musicAmount) {
        switch (musicAmount) {
            case 3:
                return 20;
            case 7:
                return 30;
            case 9:
                return 50;
            default:
                return 0;
        }
    }

}
