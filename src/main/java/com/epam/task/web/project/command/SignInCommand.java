package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SignInCommand implements Command {

    private final UserService userService;
    private InputParameterValidator inputParameterValidator = new InputParameterValidator();

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ERROR_SIGN_IN = "errorSignIn";
    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";

    private static final String REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";
    private static final String MAIN_COMMAND = "?command=main";

    public SignInCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        boolean isValidUsername = inputParameterValidator.isValid(username);
        boolean isValidPassword = inputParameterValidator.isValid(password);
        if (!isValidUsername || !isValidPassword) {
            request.setAttribute(EMPTY_INPUT_PARAMETERS, true);
            return CommandResult.forward(REGISTRATION_PAGE);
        }

        boolean isExist = userService.exist(username, password);
        if (!isExist) {
            Optional<User> optionalUser = userService.createNewUser(username, password);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession(true).setAttribute(USER, user);
                return CommandResult.redirect(MAIN_COMMAND);
            } else {
                request.setAttribute(ERROR_SIGN_IN, true);
                return CommandResult.forward(REGISTRATION_PAGE);
            }

        } else {
            request.setAttribute(ERROR_SIGN_IN, true);
            return CommandResult.forward(REGISTRATION_PAGE);
        }

    }

}
