package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmCommand implements Command{

    private final MusicOrderService musicOrderService;

    private static final String MUSIC_ORDER = "musicOrder";
    private static final String USER = "user";

    private static final String PAYED_PAGE_COMMAND = "?command=payedPage";

    public ConfirmCommand(ServiceFactory serviceFactory) {
        this.musicOrderService = (MusicOrderService) serviceFactory.create(MusicOrder.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        MusicOrder musicOrder = (MusicOrder) request.getSession(false).getAttribute(MUSIC_ORDER);
        User user = (User) request.getSession(false).getAttribute(USER);
        Long musicId = musicOrder.getMusicId();

        musicOrderService.confirmMusicOrder(musicOrder, user, musicId);

        request.getSession(false).setAttribute(USER, user);
        request.getSession(false).setAttribute(MUSIC_ORDER, musicOrder);

        return CommandResult.redirect(request.getRequestURI() + PAYED_PAGE_COMMAND);
    }
}
