package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.TitleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeleteMusicCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(DeleteMusicCommand.class);

    private static final String TITLE = "title";
    private static final String MUSIC_ID = "musicId";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String DELETE_DIALOG = "deleteDialog";
    private static final String CAN_DELETE = "canDelete";
    private static final String DELETE = "delete";
    private static final String MUSIC_REMOVED = "musicRemoved";
    private static final String INVALID_PARAMETER = "invalidParameter";
    private static final String MAIN_COMMAND = "?command=mainPage";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";

    private static final AtomicReference<DeleteMusicCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final TitleValidator titleValidator;
    private final UserService userService;
    private final MusicService musicService;

    private DeleteMusicCommand() throws CommandException {
        this.titleValidator = (TitleValidator) getValidator(TITLE);
        this.userService = (UserService) getService(User.class);
        this.musicService = (MusicService) getService(Music.class);
    }

    public static DeleteMusicCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    DeleteMusicCommand deleteMusicCommand = new DeleteMusicCommand();

                    INSTANCE.set(deleteMusicCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created DeleteMusicCommand instance");
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

        Long musicId = (Long) session.getAttribute(MUSIC_ID);

        Optional<Music> optionalMusic = musicService.getById(musicId);

        if (!optionalMusic.isPresent()) {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(MAIN_PAGE);
        }

        Music music = optionalMusic.get();

        String deleteDialogValue = request.getParameter(DELETE_DIALOG);
        String deleteValue = request.getParameter(DELETE);

        if (!titleValidator.isValid(deleteDialogValue) && !titleValidator.isValid(deleteValue)) {
            cookieManager.setCookie(response, INVALID_PARAMETER, "true");
            return CommandResult.redirect(MAIN_COMMAND);
        }

        if ("true".equals(deleteDialogValue)) {
            cookieManager.setCookie(response, CAN_DELETE, "true");
            return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
        }

        if ("true".equals(deleteValue)) {
            List<User> allClients = userService.getAllClients();

            musicService.removeMusicById(allClients, music.getId());

            request.setAttribute(MUSIC_REMOVED, true);

            return CommandResult.forward(MAIN_PAGE);

        } else {
            return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
        }
    }

}