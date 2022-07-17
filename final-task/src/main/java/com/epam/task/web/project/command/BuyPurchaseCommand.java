package com.epam.task.web.project.command;

import com.epam.task.web.project.converter.CurrencyConverter;
import com.epam.task.web.project.cookie.CookieManager;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BuyPurchaseCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(BuyPurchaseCommand.class);

    private  static final String USER = "user";
    private static final String MUSIC_ID = "musicId";
    private static final String MUSIC = "music";
    private static final String MUSIC_PRICE = "musicPrice";
    private  static final String NOT_ENOUGH_MONEY = "notEnoughMoney";
    private  static final String CAN_BUY = "canBuy";
    private  static final String DISCOUNT = "discount";
    private  static final String FINAL_PRICE = "finalPrice";
    private static final String LOCALE = "locale";
    private static final String LOGOUT_COMMAND = "?command=logout";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";

    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";

    private static final AtomicReference<BuyPurchaseCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final UserService userService;
    private final MusicService musicService;
    private final PlaylistService playlistService;
    private final MusicOrderService musicOrderService;
    private final CurrencyConverter currencyConverter;

    private BuyPurchaseCommand() throws CommandException {
        this.userService = (UserService) getService(User.class);
        this.musicService = (MusicService) getService(Music.class);
        this.musicOrderService = (MusicOrderService) getService(MusicOrder.class);
        this.playlistService = (PlaylistService) getService(Playlist.class);
        this.currencyConverter = getCurrencyConverter();
    }

    public static BuyPurchaseCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    BuyPurchaseCommand buyPurchaseCommand = new BuyPurchaseCommand();

                    INSTANCE.set(buyPurchaseCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created BuyPurchaseCommand instance");
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
                return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
            }

            boolean isEnoughCash = userService.isEnoughCash(updatedUser, music);

            if (!isEnoughCash) {
                cookieManager.setCookie(response, NOT_ENOUGH_MONEY, "true");
                return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
            }

            MusicOrder musicOrder = musicOrderService.createOrder(updatedUser, music);

            BigDecimal finalPrice = musicOrder.getFinalPrice();

            String locale = (String) session.getAttribute(LOCALE);

            Optional<BigDecimal> optionalConvertedPrice = currencyConverter.convert(music.getPrice(), locale);

            Optional<BigDecimal> optionalConvertedFinalPrice = currencyConverter.convert(finalPrice, locale);

            if (!optionalConvertedPrice.isPresent()) {
                optionalConvertedPrice = Optional.of(BigDecimal.valueOf(0.00));
            }

            request.setAttribute(CAN_BUY, true);
            request.setAttribute(MUSIC, music);
            request.setAttribute(MUSIC_PRICE, optionalConvertedPrice.get());
            request.setAttribute(FINAL_PRICE, optionalConvertedFinalPrice.get());
            request.setAttribute(DISCOUNT, musicOrder.getDiscount());

            return CommandResult.forward(PURCHASE_PAGE);

        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(MAIN_PAGE);
        }
    }

}