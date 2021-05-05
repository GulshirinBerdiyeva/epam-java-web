package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class FillBalanceCommand implements Command {

    private static final String USER = "user";
    private static final String CASH_VALUE = "cashValue";
    private static final String INVALID_NUMBER_FORMAT = "invalidNumberFormat";

    private static final String PROFILE_PAGE = "/WEB-INF/view/profile.jsp";
    private static final String PROFILE_PAGE_COMMAND = "?command=profilePage";

    private final UserService userService;
    private InputParameterValidator validator;

    public FillBalanceCommand(UserService userService, InputParameterValidator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        String cashValue = request.getParameter(CASH_VALUE);

        boolean isValid = validator.isValidNumber(cashValue);
        if (!isValid) {
            request.setAttribute(INVALID_NUMBER_FORMAT, true);
            return CommandResult.forward(PROFILE_PAGE);
        }

        BigDecimal cash = new BigDecimal(cashValue);

        userService.updateBalance(user, cash);

        session.setAttribute(USER, user);
        return CommandResult.redirect(PROFILE_PAGE_COMMAND);
    }

}
