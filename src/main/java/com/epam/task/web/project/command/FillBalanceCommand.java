package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class TopUpBalanceCommand implements Command {

    private final UserService userService;

    private static final String USER = "user";
    private static final String CASH_VALUE = "cashValue";
    private static final String PROFILE_PAGE_COMMAND = "?command=profile";

    public TopUpBalanceCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        User user = (User) request.getSession().getAttribute(USER);
        String cashValue = request.getParameter(CASH_VALUE);
        BigDecimal cash = new BigDecimal(cashValue);

        userService.updateBalance(user, cash);

        request.getSession().setAttribute(USER, user);
        return CommandResult.redirect(PROFILE_PAGE_COMMAND);
    }

}
