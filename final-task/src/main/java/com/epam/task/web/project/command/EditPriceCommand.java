package com.epam.task.web.project.command;

import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.DoubleNumberValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EditPriceCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(EditPriceCommand.class);

    private static final String DOUBLE_NUMBER = "doubleNumber";
    private static final String MUSIC_ID = "musicId";
    private static final String NEW_PRICE = "newPrice";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String INVALID_NUMBER_FORMAT = "invalidNumberFormat";

    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private static final AtomicReference<EditPriceCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final DoubleNumberValidator doubleNumberValidator;
    private final MusicService musicService;

    private EditPriceCommand() throws CommandException {
        this.doubleNumberValidator = (DoubleNumberValidator) getValidator(DOUBLE_NUMBER);
        this.musicService = (MusicService) getService(Music.class);
    }

    public static EditPriceCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    EditPriceCommand editPriceCommand = new EditPriceCommand();

                    INSTANCE.set(editPriceCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created EditPriceCommand instance");
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

        Long musicId = (Long) session.getAttribute(MUSIC_ID);

        Optional<Music> optionalMusic = musicService.getById(musicId);

        if (!optionalMusic.isPresent()) {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(MAIN_PAGE);
        }

        String priceValue = request.getParameter(NEW_PRICE);

        if (!doubleNumberValidator.isValid(priceValue)) {
            cookieManager.setCookie(response, INVALID_NUMBER_FORMAT, "true");
            return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
        }

        Music music = optionalMusic.get();

        BigDecimal newPrice = new BigDecimal(priceValue).setScale(2, RoundingMode.HALF_UP);

        musicService.updatePriceById(music.getId(), newPrice);

        return CommandResult.redirect(PURCHASE_PAGE_COMMAND);

    }

}