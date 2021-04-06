package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Albums;
import com.epam.task.web.project.service.AlbumsService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlbumsCommand implements Command {

    private final AlbumsService albumsService;

    public AlbumsCommand(ServiceFactory serviceFactory) {
        this.albumsService = (AlbumsService) serviceFactory.create(Albums.class);
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return null;
    }
}
