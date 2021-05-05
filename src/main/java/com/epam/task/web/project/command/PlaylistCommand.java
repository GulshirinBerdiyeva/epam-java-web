package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.PlaylistService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PlaylistCommand implements Command {

    private static final String USER ="user";
    private static final String PLAYLISTS ="playlists";

    private static final String PLAYLIST_PAGE = "/WEB-INF/view/playlist.jsp";

    private final PlaylistService playlistService;

    public PlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);

        List<Playlist> playlists = playlistService.getAllMusicsByUserId(user.getId());

        session.setAttribute(PLAYLISTS, playlists);
        return CommandResult.forward(PLAYLIST_PAGE);
    }

}
