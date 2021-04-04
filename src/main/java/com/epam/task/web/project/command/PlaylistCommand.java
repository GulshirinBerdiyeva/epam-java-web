package com.epam.task.web.project.command;

import com.epam.task.web.project.service.PlaylistService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlaylistCommand implements Command {

    private final PlaylistService playlistService;

    private static final String PLAYLIST = "playlist";

    public PlaylistCommand(ServiceFactory serviceFactory) {
        this.playlistService = (PlaylistService) serviceFactory.create(PLAYLIST);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
