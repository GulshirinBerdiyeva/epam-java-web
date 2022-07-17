package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.PlaylistService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
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

public class PlaylistCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(PlaylistCommand.class);

    private static final String USER ="user";
    private static final String PLAYLIST ="playlist";
    private static final String NO_PLAYLIST ="noPlaylist";

    private static final String PLAYLIST_PAGE = "/WEB-INF/view/playlist.jsp";
    private static final String LOGOUT_COMMAND = "?command=logout";

    private static final AtomicReference<PlaylistCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final UserService userService;
    private final PlaylistService playlistService;

    private PlaylistCommand() throws CommandException {
        this.userService = (UserService) getService(User.class);
        this.playlistService = (PlaylistService) getService(Playlist.class);
    }

    public static PlaylistCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    PlaylistCommand playlistCommand = new PlaylistCommand();

                    INSTANCE.set(playlistCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created PlaylistCommand instance");
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

        User user = (User) session.getAttribute(USER);
        Long userId = user.getId();

        Optional<User> optionalUser = userService.getById(userId);

        if (!optionalUser.isPresent()) {
            return CommandResult.redirect(LOGOUT_COMMAND);
        }

        List<Playlist> playlist = playlistService.getAllMusicsByUserId(userId);

        if (playlist != null && !playlist.isEmpty()) {
            request.setAttribute(PLAYLIST, playlist);

        } else {
            request.setAttribute(NO_PLAYLIST, true);
        }

        return CommandResult.forward(PLAYLIST_PAGE);

    }

}