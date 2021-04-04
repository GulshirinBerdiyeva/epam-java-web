package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command{

    private static final String LOGIN_PAGE_COMMAND = "?command=loginPage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession(false).invalidate();

        return CommandResult.redirect(request.getRequestURI() + LOGIN_PAGE_COMMAND);
    }

}
