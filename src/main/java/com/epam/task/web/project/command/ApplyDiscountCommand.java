package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.DiscountValidator;
import com.epam.task.web.project.validator.NumberValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ApplyDiscountCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(ApplyDiscountCommand.class);

    private static final String NUMBER = "number";
    private static final String DISCOUNT = "discount";
    private static final String CLIENT_ID = "clientId";
    private static final String DISCOUNT_VALUE = "discountValue";
    private static final String INVALID_NUMBER_FORMAT = "invalidNumberFormat";

    private static final String CLIENTS_PAGE_COMMAND = "?command=clientsPage";

    private static final AtomicReference<ApplyDiscountCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final DiscountValidator discountValidator;
    private final NumberValidator numberValidator;
    private final UserService userService;

    private ApplyDiscountCommand() throws CommandException {
        this.discountValidator = (DiscountValidator) getValidator(DISCOUNT);
        this.numberValidator = (NumberValidator) getValidator(NUMBER);
        this.userService = (UserService) getService(User.class);
    }

    public static ApplyDiscountCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    ApplyDiscountCommand applyDiscountCommand = new ApplyDiscountCommand();

                    INSTANCE.set(applyDiscountCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created ApplyDiscountCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String clientIdValue = request.getParameter(CLIENT_ID);
        String discountValue = request.getParameter(DISCOUNT_VALUE);

        CookieManager cookieManager = CookieManager.getInstance();

        if (numberValidator.isValid(clientIdValue) && discountValidator.isValid(discountValue)) {

            Long clientId = Long.parseLong(clientIdValue);

            int discount = Integer.parseInt(discountValue);

            userService.updateDiscount(clientId, discount);

        } else {
            cookieManager.setCookie(response, INVALID_NUMBER_FORMAT, "true");
        }

        return CommandResult.redirect(CLIENTS_PAGE_COMMAND);
    }

}