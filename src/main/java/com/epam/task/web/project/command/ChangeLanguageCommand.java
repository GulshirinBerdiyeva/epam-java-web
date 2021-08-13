package com.epam.task.web.project.command;

import com.epam.task.web.project.locale.Locale;
import com.epam.task.web.project.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChangeLanguageCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.class);

    private static final String LOCALE = "locale";
    private static final String PREVIOUS_QUERY = "previousQuery";

    private static final String LOGIN_PAGE = "/index.jsp";

    private static final AtomicReference<ChangeLanguageCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private ChangeLanguageCommand() {}

    public static ChangeLanguageCommand getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    ChangeLanguageCommand changeLanguageCommand = new ChangeLanguageCommand();

                    INSTANCE.set(changeLanguageCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created ChangeLanguageCommand instance");
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

        String locale = request.getParameter(LOCALE);

        if (!Locale.isValid(locale)) {
            locale = (String) session.getAttribute(LOCALE);
        }

        session.setAttribute(LOCALE, locale.toLowerCase());

        String query = (String) session.getAttribute(PREVIOUS_QUERY);

        return query != null ? CommandResult.redirect("?" + query) : CommandResult.forward(LOGIN_PAGE);
    }
}
