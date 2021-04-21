package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.NumberFormatValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyDiscountCommand implements Command {

    private final UserService userService;
    private NumberFormatValidator numberFormatValidator = new NumberFormatValidator();

    private static final String CLIENT_ID = "clientId";
    private static final String DISCOUNT_VALUE = "discountValue";
    private static final String INVALID_NUMBER_FORMAT = "invalidNumberFormat";

    private static final String CLIENTS_PAGE = "/WEB-INF/view/clients.jsp";
    private static final String CLIENTS_PAGE_COMMAND = "?command=clients";

    public ApplyDiscountCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long clientId = Long.valueOf(request.getParameter(CLIENT_ID));
        String discountValue = request.getParameter(DISCOUNT_VALUE);

        boolean isValid = numberFormatValidator.isValid(discountValue);
        if (isValid) {
            int discount = Integer.parseInt(discountValue);
            userService.updateDiscount(clientId, discount);

            return CommandResult.redirect(CLIENTS_PAGE_COMMAND);

        } else {
            request.setAttribute(INVALID_NUMBER_FORMAT, true);
            return CommandResult.forward(CLIENTS_PAGE);
        }

    }

}
