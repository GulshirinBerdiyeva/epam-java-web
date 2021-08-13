package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.DoubleNumberValidator;
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

public class FillBalanceCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(FillBalanceCommand.class);

    private static final String DOUBLE_NUMBER = "doubleNumber";
    private static final String USER = "user";
    private static final String CASH_VALUE = "cashValue";
    private static final String INVALID_NUMBER_FORMAT = "invalidNumberFormat";

    private static final String LOGOUT_COMMAND = "?command=logout";
    private static final String PROFILE_PAGE_COMMAND = "?command=profilePage";

    private static final AtomicReference<FillBalanceCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final DoubleNumberValidator doubleNumberValidator;
    private final UserService userService;

    private FillBalanceCommand() throws CommandException {
        this.doubleNumberValidator = (DoubleNumberValidator) getValidator(DOUBLE_NUMBER);
        this.userService = (UserService) getService(User.class);
    }

    public static FillBalanceCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    FillBalanceCommand fillBalanceCommand = new FillBalanceCommand();

                    INSTANCE.set(fillBalanceCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created FillBalanceCommand instance");
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
        CookieManager cookieManager = CookieManager.getInstance();

        User user = (User) session.getAttribute(USER);

        Optional<User> optionalUser = userService.getById(user.getId());

        if (!optionalUser.isPresent()) {
            return CommandResult.redirect(LOGOUT_COMMAND);
        }

        User updatedUser = optionalUser.get();

        String cashValue = request.getParameter(CASH_VALUE);

        if (doubleNumberValidator.isValid(cashValue)) {
            userService.updateBalance(updatedUser, new BigDecimal(cashValue));

        } else {
            cookieManager.setCookie(response, INVALID_NUMBER_FORMAT, "true");
        }

        return CommandResult.redirect(PROFILE_PAGE_COMMAND);

    }

}