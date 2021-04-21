package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.logic.CurrencyConverter;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.NumberFormatValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class EditPriceCommand implements Command{

    private final MusicService musicService;
    private NumberFormatValidator numberFormatValidator = new NumberFormatValidator();

    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String NEW_PRICE = "newPrice";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String LOCAL = "local";
    private static final String SELECTED_MUSIC_PRICE = "selectedMusicPrice";
    private static final String INVALID_NUMBER_FORMAT = "invalidNumberFormat";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";
    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";
    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";

    public EditPriceCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Music music = (Music) request.getSession().getAttribute(SELECTED_MUSIC);
        String priceValue = request.getParameter(NEW_PRICE);

        boolean isValid = numberFormatValidator.isValid(priceValue);
        if (!isValid) {
            request.setAttribute(INVALID_NUMBER_FORMAT, true);
            return CommandResult.forward(PURCHASE_PAGE);
        }

        Long musicId = music.getId();
        Optional<Music> optionalMusic = musicService.getMusicById(musicId);

        if (optionalMusic.isPresent()) {
            BigDecimal price = new BigDecimal(priceValue).setScale(2, RoundingMode.HALF_UP);
            musicService.updatePriceById(musicId, price);
            music.setPrice(price);

            String local = (String) request.getSession().getAttribute(LOCAL);
            CurrencyConverter currencyConverter = new CurrencyConverter();
            BigDecimal convertedPrice = currencyConverter.convertPrice(local, music.getPrice());

            request.getSession().setAttribute(SELECTED_MUSIC_PRICE, convertedPrice);
            request.getSession().setAttribute(SELECTED_MUSIC, music);

            return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(SEARCH_PAGE);
        }

    }

}
