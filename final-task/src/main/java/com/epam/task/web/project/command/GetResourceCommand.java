package com.epam.task.web.project.command;

import com.epam.task.web.project.data.DataException;
import com.epam.task.web.project.data.MusicFileReader;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.FileNameValidator;
import com.epam.task.web.project.validator.TitleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GetResourceCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(GetResourceCommand.class);

    private static final String TYPE = "type";
    private static final String TITLE = "title";
    private static final String FILE_NAME = "fileName";
    private static final String CURRENT_PAGE = "currentPage";
    private static final String DATA_FILE_NAME = "appResources.properties";
    private static final String INVALID_PARAMETER = "invalidParameter";
    private static final String COULD_NOT_LOAD_RESOURCES = "couldNotLoadResources";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private static final AtomicReference<GetResourceCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final TitleValidator titleValidator;
    private final FileNameValidator fileNameValidator;
    private final MusicFileReader musicFileReader;

    private GetResourceCommand() throws CommandException {
        this.titleValidator = (TitleValidator) getValidator(TITLE);
        this.fileNameValidator = (FileNameValidator) getValidator(FILE_NAME);
        this.musicFileReader = getMusicFileReader();
    }

    public static GetResourceCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    GetResourceCommand getResourceCommand = new GetResourceCommand();

                    INSTANCE.set(getResourceCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created GetResourceCommand instance");
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

        String type = request.getParameter(TYPE);
        String fileName = request.getParameter(FILE_NAME);

        if (!titleValidator.isValid(type) || !fileNameValidator.isValid(fileName)) {
            request.setAttribute(INVALID_PARAMETER, true);
            return CommandResult.forward(MAIN_PAGE);

        } else {

            try {
                musicFileReader.read(DATA_FILE_NAME, fileName, type, response);

                String page = (String) session.getAttribute(CURRENT_PAGE);

                return CommandResult.forward(page);

            } catch (DataException e) {
                request.setAttribute(COULD_NOT_LOAD_RESOURCES, true);
                return CommandResult.forward(MAIN_PAGE);
            }
        }
    }

}