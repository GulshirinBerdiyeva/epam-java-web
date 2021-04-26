package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfirmPurchaseCommand implements Command{

    private final MusicOrderService musicOrderService;
    private final PlaylistService playlistService;

    private static final String MUSIC_ORDER = "musicOrder";
    private static final String USER = "user";
    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String PAYED = "payed";
    private  static final String EXIST_IN_PLAYLIST = "existInPlaylist";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";

    public ConfirmPurchaseCommand(MusicOrderService musicOrderService, PlaylistService playlistService) {
        this.musicOrderService = musicOrderService;
        this.playlistService = playlistService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Music music = (Music) session.getAttribute(SELECTED_MUSIC);
        MusicOrder musicOrder = (MusicOrder) session.getAttribute(MUSIC_ORDER);

        boolean isExistInPlaylist = playlistService.exist(user.getId(), music.getId());

        if (isExistInPlaylist) {
            request.setAttribute(EXIST_IN_PLAYLIST, true);
        } else {
            musicOrderService.confirmMusicOrder(musicOrder, user, music);

            session.setAttribute(USER, user);
            session.setAttribute(MUSIC_ORDER, musicOrder);
            request.setAttribute(PAYED, true);
        }

        return CommandResult.forward(PURCHASE_PAGE);
    }

}
