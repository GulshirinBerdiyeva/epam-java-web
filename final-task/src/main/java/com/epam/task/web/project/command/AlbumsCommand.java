package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlbumsCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(AlbumsCommand.class);

    private static final String ALBUMS_TITLE = "albumsTitle";
    private static final String NO_ALBUMS = "noAlbums";

    private static final String ALBUMS_PAGE = "/WEB-INF/view/albums.jsp";

    private static final AtomicReference<AlbumsCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final AlbumService albumsService;

    private AlbumsCommand() throws CommandException {
        this.albumsService = (AlbumService) getService(Album.class);
    }

    public static AlbumsCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    AlbumsCommand albumsCommand = new AlbumsCommand();

                    INSTANCE.set(albumsCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created AlbumsCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Album> albums = albumsService.getAll();

        if (albums != null && !albums.isEmpty()) {
            HashSet<String> albumsTitle = new HashSet<>();

            albums.forEach(album -> albumsTitle.add(album.getAlbumTitle()));

            request.setAttribute(ALBUMS_TITLE, albumsTitle);

        } else {
            request.setAttribute(NO_ALBUMS, true);
        }

        return CommandResult.forward(ALBUMS_PAGE);
    }

}