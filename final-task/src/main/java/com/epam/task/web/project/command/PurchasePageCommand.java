package com.epam.task.web.project.command;

import com.epam.task.web.project.converter.CurrencyConverter;
import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.locale.DateTimeLocalizer;
import com.epam.task.web.project.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PurchasePageCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(PurchasePageCommand.class);

    private static final String DATE_TIME = "dateTime";
    private static final String USER = "user";
    private static final String MUSIC = "music";
    private static final String MUSIC_ID = "musicId";
    private static final String MUSIC_PRICE = "musicPrice";
    private static final String MUSIC_COMMENTS = "musicComments";
    private static final String LOCALE = "locale";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String PAYED = "payed";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String LOGOUT_COMMAND = "?command=logout";
    private static final String MAIN_COMMAND = "?command=mainPage";
    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";

    private static final AtomicReference<PurchasePageCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final UserService userService;
    private final MusicService musicService;
    private final PlaylistService playlistService;
    private final CommentService commentService;
    private final DateTimeLocalizer dateTimeLocalizer;
    private final CurrencyConverter currencyConverter;

    private PurchasePageCommand() throws CommandException {
        this.userService = (UserService) getService(User.class);
        this.musicService = (MusicService) getService(Music.class);
        this.commentService = (CommentService) getService(Comment.class);
        this.playlistService = (PlaylistService) getService(Playlist.class);
        this.dateTimeLocalizer = (DateTimeLocalizer) getLocalizer(DATE_TIME);
        this.currencyConverter = getCurrencyConverter();
    }

    public static PurchasePageCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    PurchasePageCommand purchasePageCommand = new PurchasePageCommand();

                    INSTANCE.set(purchasePageCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created PurchasePageCommand instance");
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
        Long musicId = (Long) session.getAttribute(MUSIC_ID);

        Optional<User> optionalUser = userService.getById(user.getId());
        Optional<Music> optionalMusic = musicService.getById(musicId);

        if (!optionalUser.isPresent()) {
            return CommandResult.redirect(LOGOUT_COMMAND);
        }

        if (optionalMusic.isPresent()) {
            User updatedUser = optionalUser.get();
            Music music = optionalMusic.get();

            boolean exist = playlistService.exist(updatedUser.getId(), music.getId());

            if (exist) {
                request.setAttribute(PAYED, true);
            }

            List<Comment> comments = commentService.getCommentsByMusicId(music.getId());

            if (comments != null) {
                String locale = (String) session.getAttribute(LOCALE);

                for (Comment comment : comments) {
                    Optional<String> optionalDateTime = dateTimeLocalizer.localize(comment.getDateTime(), locale);

                    optionalDateTime.ifPresent(comment::setLocaleDateTime);
                }

                request.setAttribute(MUSIC_COMMENTS, comments);

                Optional<BigDecimal> optionalConvertedPrice = currencyConverter.convert(music.getPrice(), locale);

                if (!optionalConvertedPrice.isPresent()) {
                    optionalConvertedPrice = Optional.of(BigDecimal.valueOf(0.00));
                }

                request.setAttribute(MUSIC, music);
                request.setAttribute(MUSIC_PRICE, optionalConvertedPrice.get());

                return CommandResult.forward(PURCHASE_PAGE);
            }

        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(MAIN_PAGE);
        }

        return CommandResult.redirect(MAIN_COMMAND);
    }

}
