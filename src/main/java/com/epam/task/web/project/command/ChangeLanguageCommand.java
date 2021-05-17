package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.Role;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.localizer.DateTimeLocalizer;
import com.epam.task.web.project.logic.CurrencyConverter;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public class ChangeLanguageCommand implements Command{

    private static final String FRANCE = "france";
    private static final String RUSSIAN = "russian";
    private static final String ENGLISH_LOCAL = "us";
    private static final String FRANCE_LOCAL = "fr";
    private static final String RUSSIAN_LOCAL = "ru";
    private static final String LANGUAGE = "language";
    private static final String LOCAL = "local";
    private static final String CURRENT_PAGE = "currentPage";
    private  static final String USER = "user";
    private  static final String USER_CASH = "userCash";
    private  static final String SELECTED_MUSIC = "selectedMusic";
    private static final String SELECTED_MUSIC_PRICE = "selectedMusicPrice";
    private static final String SELECTED_MUSIC_COMMENTS = "selectedMusicComments";

    private static final String LOGIN_PAGE = "/index.jsp";

    private InputParameterValidator validator;

    public ChangeLanguageCommand(InputParameterValidator validator) {
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String language = request.getParameter(LANGUAGE);

        boolean isValid = validator.isStringValid(language);
        if (!isValid) {
            session.setAttribute(LOCAL, ENGLISH_LOCAL);

        } else {
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
        }

        String locale = (String) session.getAttribute(LOCAL);
        localizeUserCash(session, locale);
        localizeMusicPrice(session, locale);
        localizeMusicComments(session, locale);

        String page = (String) session.getAttribute(CURRENT_PAGE);
        return page != null ? CommandResult.forward(page) : CommandResult.forward(LOGIN_PAGE);

    }

    private void localizeUserCash(HttpSession session, String locale) {
        User user = (User) session.getAttribute(USER);
        if (user != null && Role.CLIENT.equals(user.getRole())) {
            BigDecimal cash = user.getCash();
            BigDecimal convertedCash = CurrencyConverter.convertCurrency(locale, cash);

            session.setAttribute(USER_CASH, convertedCash);
        }
    }

    private void localizeMusicPrice(HttpSession session, String locale) {
        Music music = (Music) session.getAttribute(SELECTED_MUSIC);
        if (music != null) {
            BigDecimal price = music.getPrice();
            BigDecimal convertedPrice = CurrencyConverter.convertCurrency(locale, price);

            session.setAttribute(SELECTED_MUSIC_PRICE, convertedPrice);
        }
    }

    private void localizeMusicComments(HttpSession session, String locale) {
        List<Comment> comments = (List<Comment>) session.getAttribute(SELECTED_MUSIC_COMMENTS);
        if (comments != null) {
            DateTimeLocalizer localizer = new DateTimeLocalizer(locale);

            for (Comment comment : comments) {
                String localizedDateTime = localizer.localizeDateTime(comment.getDateTime());
                comment.setLocaleDateTime(localizedDateTime);
            }
        }
    }

}