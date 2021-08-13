package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.CommentService;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;
import com.epam.task.web.project.validator.CommentValidator;
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

public class CreateCommentCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(CreateCommentCommand.class);

    private static final String COMMENT = "comment";
    private static final String USER = "user";
    private static final String MUSIC_ID = "musicId";
    private static final String NEW_COMMENT = "newComment";
    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";
    private static final String LOGOUT_COMMAND = "?command=logout";

    private static final AtomicReference<CreateCommentCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final CommentValidator commentValidator;
    private final UserService userService;
    private final MusicService musicService;
    private final CommentService commentService;

    private CreateCommentCommand() throws CommandException {
        this.commentValidator = (CommentValidator) getValidator(COMMENT);
        this.userService = (UserService) getService(User.class);
        this.musicService = (MusicService) getService(Music.class);
        this.commentService = (CommentService) getService(Comment.class);
    }

    public static CreateCommentCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    CreateCommentCommand createCommentCommand = new CreateCommentCommand();

                    INSTANCE.set(createCommentCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created CreateCommentCommand instance");
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

        Optional<User> optionalUser = userService.getById(user.getId());

        if (!optionalUser.isPresent()) {
            return CommandResult.redirect(LOGOUT_COMMAND);
        }

        User updatedUser = optionalUser.get();

        Long musicId = (Long) session.getAttribute(MUSIC_ID);

        Optional<Music> optionalMusic = musicService.getById(musicId);

        if (!optionalMusic.isPresent()) {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(MAIN_PAGE);
        }

        Music music = optionalMusic.get();

        String newComment = request.getParameter(NEW_COMMENT);

        if (commentValidator.isValid(newComment)) {
            Comment comment = new Comment(updatedUser.getId(), music.getId(), newComment);

            commentService.save(comment);

            return CommandResult.redirect(PURCHASE_PAGE_COMMAND);

        } else {
            cookieManager.setCookie(response, EMPTY_INPUT_PARAMETERS, "true");
            return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
        }

    }

}