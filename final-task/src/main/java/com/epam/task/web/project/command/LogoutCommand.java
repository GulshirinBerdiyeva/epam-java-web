package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogoutCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);

    private static final String LOGIN_PAGE = "/index.jsp";

    private static final AtomicReference<LogoutCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private LogoutCommand(){}

    public static LogoutCommand getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    LogoutCommand logoutCommand = new LogoutCommand();

                    INSTANCE.set(logoutCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created LogoutCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.getSession().invalidate();
        return CommandResult.forward(LOGIN_PAGE);
    }

}