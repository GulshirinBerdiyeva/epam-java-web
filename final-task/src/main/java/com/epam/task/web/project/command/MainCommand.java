package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
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

public class MainCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(MainCommand.class);

    private static final String MUSICS = "musics";
    private static final String NO_MUSICS = "noMusics";
    private static final String MUSIC = "music";
    private static final String SHOW_MUSICS = "showMusics";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private static final AtomicReference<MainCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final MusicService musicService;

    private MainCommand() throws CommandException {
        this.musicService = (MusicService) getService(Music.class);
    }

    public static MainCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    MainCommand mainCommand = new MainCommand();

                    INSTANCE.set(mainCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created MainCommand instance");
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

        List<Music> musics = musicService.getAll();

        if (musics != null && !musics.isEmpty()) {
            request.setAttribute(SHOW_MUSICS, true);
            request.setAttribute(MUSIC, musics.get(0));
            session.setAttribute(MUSICS, musics);

        } else {
            request.setAttribute(NO_MUSICS, true);
        }

        return CommandResult.forward(MAIN_PAGE);
    }

}