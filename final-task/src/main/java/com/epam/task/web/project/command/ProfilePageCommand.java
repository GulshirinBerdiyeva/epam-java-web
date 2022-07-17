package com.epam.task.web.project.command;

import com.epam.task.web.project.converter.CurrencyConverter;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProfilePageCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(ProfilePageCommand.class);

    private static final String USER = "user";
    private static final String USER_CASH = "userCash";
    private static final String LOCALE = "locale";

    private static final String PROFILE_PAGE = "/WEB-INF/view/profile.jsp";
    private static final String LOGOUT_COMMAND = "?command=logout";

    private static final AtomicReference<ProfilePageCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final UserService userService;
    private final CurrencyConverter currencyConverter;

    private ProfilePageCommand() throws CommandException {
        this.userService = (UserService) getService(User.class);
        this.currencyConverter = getCurrencyConverter();
    }

    public static ProfilePageCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    ProfilePageCommand profilePageCommand = new ProfilePageCommand();

                    INSTANCE.set(profilePageCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created ProfilePageCommand instance");
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

        User user = (User) session.getAttribute(USER);
        Long userId = user.getId();

        Optional<User> optionalUser = userService.getById(userId);

        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();

            session.setAttribute(USER, updatedUser);

            String locale = (String) session.getAttribute(LOCALE);

            Optional<BigDecimal> optionalConvertedCash = currencyConverter.convert(updatedUser.getCash(), locale);

            if (!optionalConvertedCash.isPresent()) {
                optionalConvertedCash = Optional.of(BigDecimal.valueOf(0.00));
            }

            request.setAttribute(USER_CASH, optionalConvertedCash.get());
            return CommandResult.forward(PROFILE_PAGE);

        } else {
            return CommandResult.redirect(LOGOUT_COMMAND);
        }
    }

}
