package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.NumberValidator;
import com.epam.task.web.project.validator.TitleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GetMusicCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(GetMusicCommand.class);

    private static final String TITLE = "title";
    private static final String NUMBER = "number";
    private static final String TYPE = "type";
    private static final String PAGE = "page";
    private static final String ALBUM = "album";
    private static final String MUSICS = "musics";
    private static final String MUSIC = "music";
    private static final String SEARCHING_MUSICS = "searchingMusics";
    private static final String INVALID_PARAMETER = "invalidParameter";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String MAIN_COMMAND = "?command=mainPage";

    private static final AtomicReference<GetMusicCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final TitleValidator titleValidator;
    private final NumberValidator numberValidator;

    private GetMusicCommand() throws CommandException {
        this.titleValidator  = (TitleValidator) getValidator(TITLE);
        this.numberValidator  = (NumberValidator) getValidator(NUMBER);
    }

    public static GetMusicCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    GetMusicCommand getMusicCommand = new GetMusicCommand();

                    INSTANCE.set(getMusicCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created GetMusicCommand instance");
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
        String pageValue = request.getParameter(PAGE);

        if (!titleValidator.isValid(type) || !numberValidator.isValid(pageValue)) {
            request.setAttribute(INVALID_PARAMETER, true);
            return CommandResult.forward(MAIN_PAGE);
        }

        List<Music> musics;

        switch (type) {
            case MUSICS:
                musics = (List<Music>) session.getAttribute(MUSICS);
                break;
            case ALBUM:
                musics = (List<Music>) session.getAttribute(ALBUM);
                break;
            case SEARCHING_MUSICS:
                musics = (List<Music>) session.getAttribute(SEARCHING_MUSICS);
                break;
            default:
                throw new IllegalArgumentException("Unknown type of musics description! \"" + type + "\"");
        }

        int page;

        if (musics != null && !musics.isEmpty()) {

            page = Integer.parseInt(pageValue);

            if (page > musics.size()) {
                page = 1;
            }

        } else {
            return CommandResult.redirect(MAIN_COMMAND);
        }

        Music music = musics.get(page - 1);

        request.setAttribute(MUSIC, music);
        request.setAttribute(PAGE, page);

        return CommandResult.forward(MAIN_PAGE);
    }

}