package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.FullNameValidator;
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

public class SearchMusicCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(SearchMusicCommand.class);

    private static final String ARTIST = "artist";
    private static final String FULL_NAME = "fullName";
    private static final String TITLE = "title";
    private static final String SEARCHING_MUSICS = "searchingMusics";
    private static final String MUSIC = "music";
    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String SHOW_SEARCHING_MUSICS = "showSearchingMusics";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String MAIN_COMMAND = "?command=mainPage";

    private static final AtomicReference<SearchMusicCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final FullNameValidator fullNameValidator;
    private final TitleValidator titleValidator;
    private final MusicService musicService;

    private SearchMusicCommand() throws CommandException {
        this.fullNameValidator = (FullNameValidator) getValidator(FULL_NAME);
        this.titleValidator = (TitleValidator) getValidator(TITLE);
        this.musicService = (MusicService) getService(Music.class);
    }

    public static SearchMusicCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    SearchMusicCommand searchMusicCommand = new SearchMusicCommand();

                    INSTANCE.set(searchMusicCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created SearchMusicCommand instance");
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

        String artistValue = request.getParameter(ARTIST);
        String titleValue = request.getParameter(TITLE);

        boolean isValidArtistValue = fullNameValidator.isValid(artistValue);
        boolean isValidTitleValue = titleValidator.isValid(titleValue);

        if (!isValidArtistValue && !isValidTitleValue) {
            cookieManager.setCookie(response, EMPTY_INPUT_PARAMETERS, "true");
            return CommandResult.redirect(MAIN_COMMAND);
        }

        List<Music> musics;

        if (isValidArtistValue) {
            musics = musicService.getMusicsBySearchParameter(ARTIST, artistValue);

        } else {
            musics = musicService.getMusicsBySearchParameter(TITLE, titleValue);
        }

        if (musics != null && !musics.isEmpty()) {
            request.setAttribute(SHOW_SEARCHING_MUSICS, true);
            request.setAttribute(MUSIC, musics.get(0));
            session.setAttribute(SEARCHING_MUSICS, musics);

        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
        }

        return CommandResult.forward(MAIN_PAGE);
    }

}