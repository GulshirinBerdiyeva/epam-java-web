package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command{

    private static final String LOGIN_PAGE = "/index.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        session.invalidate();

        return CommandResult.forward(LOGIN_PAGE);
    }

}
