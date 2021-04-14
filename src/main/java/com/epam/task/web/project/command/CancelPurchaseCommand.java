package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.service.MusicOrderService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelPurchaseCommand implements Command{

    private final MusicOrderService musicOrderService;

    private static final String MUSIC_ORDER = "musicOrder";
    private static final String PAID = "paid";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/fragments/purchase.jsp";
    private static final String COMMENTS_COMMAND = "?command=comments";

    public CancelPurchaseCommand(MusicOrderService musicOrderService) {
        this.musicOrderService = musicOrderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        MusicOrder musicOrder = (MusicOrder) request.getSession(false).getAttribute(MUSIC_ORDER);

        if (musicOrder.isPayment()) {
            request.setAttribute(PAID, true);
            return CommandResult.forward(PURCHASE_PAGE);
        } else {
            musicOrderService.cancelMusicOrder(musicOrder);
            return CommandResult.redirect(request.getRequestURI() + COMMENTS_COMMAND);
        }
    }

}
