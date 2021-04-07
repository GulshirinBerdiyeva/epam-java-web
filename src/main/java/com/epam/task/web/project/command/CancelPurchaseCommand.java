package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.service.MusicOrderService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelPurchaseCommand implements Command{

    private final MusicOrderService musicOrderService;

    private static final String MUSIC_ORDER = "musicOrder";

    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";

    public CancelPurchaseCommand(ServiceFactory serviceFactory) {
        this.musicOrderService = (MusicOrderService) serviceFactory.create(MusicOrder.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        MusicOrder musicOrder = (MusicOrder) request.getSession(false).getAttribute(MUSIC_ORDER);

        musicOrder.setPayment(false);
        musicOrderService.cancelMusicOrder(musicOrder);

        request.getSession(false).setAttribute(MUSIC_ORDER, musicOrder);

        return CommandResult.redirect(request.getRequestURI() + PURCHASE_PAGE_COMMAND);

    }

}
