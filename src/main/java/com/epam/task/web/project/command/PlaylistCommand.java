package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.service.PlaylistService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlaylistCommand implements Command {

    private final PlaylistService playlistService;

    public PlaylistCommand(ServiceFactory serviceFactory) {
        this.playlistService = (PlaylistService) serviceFactory.create(Playlist.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
