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

public class ShowPageCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(ShowPageCommand.class);

    private static final AtomicReference<ShowPageCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private String page;

    private ShowPageCommand() {}

    public void setPage(String page) {
        this.page = page;
    }

    public static ShowPageCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    ShowPageCommand showPageCommand = new ShowPageCommand();

                    INSTANCE.set(showPageCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created ShowPageCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.forward(page);
    }

}