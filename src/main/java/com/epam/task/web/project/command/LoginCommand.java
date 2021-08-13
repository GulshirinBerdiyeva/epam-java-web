package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.FullNameValidator;
import com.epam.task.web.project.validator.PasswordValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoginCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

    private static final String USERNAME = "username";
    private static final String FULL_NAME = "fullName";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String ERROR_LOGIN = "errorLogin";

    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String MAIN_COMMAND = "?command=mainPage";

    private static final AtomicReference<LoginCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final FullNameValidator fullNameValidator;
    private final PasswordValidator passwordValidator;
    private final UserService userService;

    private LoginCommand() throws CommandException {
        this.fullNameValidator = (FullNameValidator) getValidator(FULL_NAME);
        this.passwordValidator = (PasswordValidator) getValidator(PASSWORD);
        this.userService = (UserService) getService(User.class);
    }

    public static LoginCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    LoginCommand loginCommand = new LoginCommand();

                    INSTANCE.set(loginCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created LoginCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();

        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        if (!fullNameValidator.isValid(username) || !passwordValidator.isValid(password)) {
            request.setAttribute(ERROR_LOGIN, true);
            return CommandResult.forward(LOGIN_PAGE);
        }

        Optional<User> optionalUser = userService.login(username, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            session.setAttribute(USER, user);
            return CommandResult.redirect(MAIN_COMMAND);

        } else {
            request.setAttribute(ERROR_LOGIN, true);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }

}