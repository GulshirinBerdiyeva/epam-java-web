package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.TitleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlbumMusicsCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(AlbumMusicsCommand.class);

    private static final String TITLE = "title";
    private static final String SELECTED_ALBUM_TITLE = "selectedAlbumTitle";
    private static final String ALBUM = "album";
    private static final String MUSIC = "music";
    private static final String SHOW_ALBUM = "showAlbum";
    private static final String INVALID_PARAMETER = "invalidParameter";
    private static final String NO_MUSICS = "noMusics";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private static final AtomicReference<AlbumMusicsCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final TitleValidator titleValidator;
    private final AlbumService albumService;

    private AlbumMusicsCommand() throws CommandException {
        this.titleValidator = (TitleValidator) getValidator(TITLE);
        this.albumService = (AlbumService) getService(Album.class);
    }

    public static AlbumMusicsCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    AlbumMusicsCommand albumMusicsCommand = new AlbumMusicsCommand();

                    INSTANCE.set(albumMusicsCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created AlbumMusicsCommand instance");
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

        String albumTitle = request.getParameter(SELECTED_ALBUM_TITLE);

        if (!titleValidator.isValid(albumTitle)) {
            request.setAttribute(INVALID_PARAMETER, true);

            return CommandResult.forward(MAIN_PAGE);
        }

        List<Album> albums = albumService.getAllMusicsByAlbumTitle(albumTitle);

        if (albums != null && !albums.isEmpty()) {
            List<Music> musics = new ArrayList<>();

            albums.stream()
                  .forEach(albumMusic -> musics.add(albumMusic.getMusic()));

            request.setAttribute(SHOW_ALBUM, true);
            request.setAttribute(MUSIC, musics.get(0));
            session.setAttribute(ALBUM, musics);

        } else {
            request.setAttribute(NO_MUSICS, true);
        }

        return CommandResult.forward(MAIN_PAGE);
    }

}