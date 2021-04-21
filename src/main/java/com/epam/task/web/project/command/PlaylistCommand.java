package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.PlaylistService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PlaylistCommand implements Command {

    private final PlaylistService playlistService;

    private static final String USER ="user";
    private static final String PLAYLISTS ="playlists";
    private static final String PLAYLIST_PAGE = "/WEB-INF/view/playlist.jsp";

    public PlaylistCommand(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession().getAttribute(USER);
        Long userId = user.getId();

        List<Playlist> playlists = playlistService.getAllMusicsByUserId(userId);

        request.getSession().setAttribute(PLAYLISTS, playlists);
        return CommandResult.forward(PLAYLIST_PAGE);
    }

}
