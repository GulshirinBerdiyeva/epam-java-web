package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.service.MusicOrderService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelCommand implements Command{

    private final MusicOrderService musicOrderService;

    private static final String MUSIC_ORDER = "musicOrder";

    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";

    public CancelCommand(ServiceFactory serviceFactory) {
        this.musicOrderService = (MusicOrderService) serviceFactory.create(MusicOrder.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        MusicOrder musicOrder = (MusicOrder) request.getSession(false).getAttribute(MUSIC_ORDER);

        musicOrderService.cancelMusicOrder(musicOrder);

        return CommandResult.redirect(request.getRequestURI() + PURCHASE_PAGE_COMMAND);
    }

}
