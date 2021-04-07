package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlbumsCommand implements Command {

    private final AlbumService albumsService;

    public AlbumsCommand(ServiceFactory serviceFactory) {
        this.albumsService = (AlbumService) serviceFactory.create(Album.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
