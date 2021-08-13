package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientsCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(ClientsCommand.class);

    private static final String CLIENTS = "clients";
    private static final String NO_CLIENTS = "noClients";

    private static final String CLIENTS_PAGE = "/WEB-INF/view/clients.jsp";

    private static final AtomicReference<ClientsCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final UserService userService;

    private ClientsCommand() throws CommandException {
        this.userService = (UserService) getService(User.class);
    }

    public static ClientsCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    ClientsCommand clientsCommand = new ClientsCommand();

                    INSTANCE.set(clientsCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created ClientsCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> clients = userService.getAllClients();

        if (clients != null && !clients.isEmpty()) {
            request.setAttribute(CLIENTS, clients);

        } else {
            request.setAttribute(NO_CLIENTS, true);
        }

        return CommandResult.forward(CLIENTS_PAGE);
    }

}