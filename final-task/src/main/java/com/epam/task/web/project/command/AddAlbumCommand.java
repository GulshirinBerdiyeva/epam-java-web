package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.TitleValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class AddAlbumCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddAlbumCommand.class);

    private static final String TITLE = "title";
    private static final String ALBUM_TITLE = "albumTitle";
    private static final String ALBUM_ELEMENTS = "albumElements";
    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";

    private static final String ALBUMS_PAGE_COMMAND = "?command=albumsPage";
    private static final String CREATE_ALBUM_PAGE_COMMAND = "?command=createAlbumPage";

    private static final AtomicReference<AddAlbumCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final TitleValidator titleValidator;
    private final AlbumService albumService;

    private AddAlbumCommand() throws CommandException {
        this.titleValidator = (TitleValidator) getValidator(TITLE);
        this.albumService = (AlbumService) getService(Album.class);
    }

    public static AddAlbumCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    AddAlbumCommand addAlbumCommand = new AddAlbumCommand();

                    INSTANCE.set(addAlbumCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created AddAlbumCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String albumTitle = request.getParameter(ALBUM_TITLE);
        String[] selectedMusicsId = request.getParameterValues(ALBUM_ELEMENTS);

        CookieManager cookieManager = CookieManager.getInstance();

        if (!titleValidator.isValid(albumTitle) || selectedMusicsId == null) {
            cookieManager.setCookie(response, EMPTY_INPUT_PARAMETERS, "true");
            return CommandResult.redirect(CREATE_ALBUM_PAGE_COMMAND);
        }

        boolean exist = albumService.exist(albumTitle);

        if (exist) {
            albumService.removeByTitle(albumTitle);
        }

        List<Long> musicsId = Arrays.stream(selectedMusicsId)
                                    .map(Long::parseLong)
                                    .collect(Collectors.toList());

        for (Long musicId : musicsId) {
            albumService.save(new Album(musicId, albumTitle));
        }

        return CommandResult.redirect(ALBUMS_PAGE_COMMAND);
    }

}