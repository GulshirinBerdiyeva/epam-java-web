package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyDiscountCommand implements Command {

    private final UserService userService;

    private static final String CLIENT_ID = "clientId";
    private static final String DISCOUNT_VALUE = "discountValue";
    private static final String CLIENTS_PAGE_COMMAND = "?command=clients";

    public ApplyDiscountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long clientId = Long.valueOf(request.getParameter(CLIENT_ID));
        int discountValue = Integer.parseInt(request.getParameter(DISCOUNT_VALUE));

        userService.updateDiscount(clientId, discountValue);

        return CommandResult.redirect(request.getRequestURI() + CLIENTS_PAGE_COMMAND);
    }

}
