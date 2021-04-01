package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAlbumCommand implements Command{

    private static final String ALBUMS_PAGE = "/WEB-INF/view/fragments/albums.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession(false).setAttribute("selectMusic", true);

        return CommandResult.forward(ALBUMS_PAGE);
    }
}
