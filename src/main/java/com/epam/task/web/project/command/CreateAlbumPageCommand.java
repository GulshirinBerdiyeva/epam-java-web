package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateAlbumPageCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(CreateAlbumPageCommand.class);

    private static final String MUSICS = "musics";
    private static final String NO_MUSICS = "noMusics";

    private static final String CREATE_ALBUM_PAGE = "/WEB-INF/view/createAlbum.jsp";

    private static final AtomicReference<CreateAlbumPageCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final MusicService musicService;

    private CreateAlbumPageCommand() throws CommandException {
        this.musicService = (MusicService) getService(Music.class);
    }

    public static CreateAlbumPageCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    CreateAlbumPageCommand createAlbumPageCommand = new CreateAlbumPageCommand();

                    INSTANCE.set(createAlbumPageCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created CreateAlbumPageCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }



    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Music> musics = musicService.getAll();

        if (musics != null && !musics.isEmpty()) {
            request.setAttribute(MUSICS, musics);

        } else {
            request.setAttribute(NO_MUSICS, true);
        }

        return CommandResult.forward(CREATE_ALBUM_PAGE);
    }
}
