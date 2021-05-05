package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientsCommand implements Command {

    private static final String CLIENTS = "clients";
    private static final String CLIENTS_PAGE = "/WEB-INF/view/clients.jsp";

    private final UserService userService;

    public ClientsCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> clients = userService.getAllClients();

        request.getSession().setAttribute(CLIENTS, clients);
        return CommandResult.forward(CLIENTS_PAGE);
    }

}
