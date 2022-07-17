package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.NumberValidator;
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

public class SelectMusicCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(SelectMusicCommand.class);

    private static final String NUMBER = "number";
    private static final String SELECTED_MUSIC_ID = "selectedMusicId";
    private static final String MUSIC_ID = "musicId";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String INVALID_PARAMETER = "invalidParameter";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String MAIN_COMMAND = "?command=mainPage";
    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";

    private static final AtomicReference<SelectMusicCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final NumberValidator numberValidator;
    private final MusicService musicService;

    private SelectMusicCommand() throws CommandException {
        this.numberValidator = (NumberValidator) getValidator(NUMBER);
        this.musicService = (MusicService) getService(Music.class);
    }

    public static SelectMusicCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    SelectMusicCommand selectMusicCommand = new SelectMusicCommand();

                    INSTANCE.set(selectMusicCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created SelectMusicCommand instance");
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

        String idValue = request.getParameter(SELECTED_MUSIC_ID);

        if (numberValidator.isValid(idValue)) {
            Long id = Long.parseLong(idValue);

            Optional<Music> optionalMusic = musicService.getById(id);

            if (optionalMusic.isPresent()) {
                Music music = optionalMusic.get();

                session.setAttribute(MUSIC_ID, music.getId());
                return CommandResult.redirect(PURCHASE_PAGE_COMMAND);

            } else {
                request.setAttribute(MUSIC_IS_ABSENT, true);
                return CommandResult.forward(MAIN_PAGE);
            }

        } else {
            cookieManager.setCookie(response, INVALID_PARAMETER, "true");
            return CommandResult.redirect(MAIN_COMMAND);
        }
    }

}