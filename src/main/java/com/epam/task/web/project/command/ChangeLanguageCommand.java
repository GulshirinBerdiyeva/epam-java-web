package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.logic.CurrencyConverter;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class ChangeLanguageCommand implements Command{

    private static final String FRANCE = "france";
    private static final String RUSSIAN = "russian";
    private static final String ENGLISH_LOCAL = "US";
    private static final String FRANCE_LOCAL = "FR";
    private static final String RUSSIAN_LOCAL = "RU";

    private static final String LANGUAGE = "language";
    private static final String LOCAL = "local";
    private static final String CURRENT_PAGE = "currentPage";
    private  static final String SELECTED_MUSIC = "selectedMusic";
    private static final String SELECTED_MUSIC_PRICE = "selectedMusicPrice";

    private static final String LOGIN_PAGE = "/index.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String language = request.getParameter(LANGUAGE);
        HttpSession session = request.getSession(true);

        switch (language) {
            case FRANCE:
                session.setAttribute(LOCAL, FRANCE_LOCAL);
                break;
            case RUSSIAN:
                session.setAttribute(LOCAL, RUSSIAN_LOCAL);
                break;
            default:
                session.setAttribute(LOCAL, ENGLISH_LOCAL);
        }

        Music music = (Music) request.getSession().getAttribute(SELECTED_MUSIC);
        if (music != null) {
            String local = (String) session.getAttribute(LOCAL);

            CurrencyConverter currencyConverter = new CurrencyConverter();
            BigDecimal convertedPrice = currencyConverter.convertPrice(local, music.getPrice());
            request.getSession().setAttribute(SELECTED_MUSIC_PRICE, convertedPrice);
        }

        String page = (String) session.getAttribute(CURRENT_PAGE);
        return page == null ? CommandResult.forward(LOGIN_PAGE) : CommandResult.forward(page);
    }

}
