package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.*;
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

public class ConfirmPurchaseCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmPurchaseCommand.class);

    private static final String USER = "user";
    private static final String MUSIC_ID = "musicId";
    private  static final String EXIST_IN_PLAYLIST = "existInPlaylist";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";
    private static final String LOGOUT_COMMAND = "?command=logout";

    private static final AtomicReference<ConfirmPurchaseCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final UserService userService;
    private final MusicService musicService;
    private final MusicOrderService musicOrderService;
    private final PlaylistService playlistService;

    private ConfirmPurchaseCommand() throws CommandException {
        this.userService = (UserService) getService(User.class);
        this.musicService = (MusicService) getService(Music.class);
        this.musicOrderService = (MusicOrderService) getService(MusicOrder.class);
        this.playlistService = (PlaylistService) getService(Playlist.class);
    }

    public static ConfirmPurchaseCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    ConfirmPurchaseCommand confirmPurchaseCommand = new ConfirmPurchaseCommand();

                    INSTANCE.set(confirmPurchaseCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created ConfirmPurchaseCommand instance");
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
        Long musicId = (Long) session.getAttribute(MUSIC_ID);

        Optional<User> optionalUser = userService.getById(user.getId());
        Optional<Music> optionalMusic = musicService.getById(musicId);

        if (!optionalUser.isPresent()) {
            return CommandResult.redirect(LOGOUT_COMMAND);
        }

        if (optionalMusic.isPresent()) {
            User updatedUser = optionalUser.get();
            Music music = optionalMusic.get();

            MusicOrder musicOrder = musicOrderService.createOrder(updatedUser, music);

            boolean exist = playlistService.exist(updatedUser.getId(), music.getId());

            if (!exist) {
                musicOrderService.confirmMusicOrder(musicOrder, updatedUser, music);

                session.setAttribute(USER, updatedUser);

            } else {
                cookieManager.setCookie(response, EXIST_IN_PLAYLIST, "true");
            }

            return CommandResult.redirect(PURCHASE_PAGE_COMMAND);

        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(MAIN_PAGE);
        }
    }

}