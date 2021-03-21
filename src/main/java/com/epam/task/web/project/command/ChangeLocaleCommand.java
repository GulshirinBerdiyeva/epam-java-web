package com.epam.task.web.project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command{

    private final String locale;

    private static final String LOCALE = "locale";
    private static final String RUSSIAN = "russian";
    private static final String ENGLISH_LOCALE = "EN";
    private static final String RUSSIAN_LOCALE = "RU";

    private static final String LOGIN_PAGE = "/index.jsp";

    public ChangeLocaleCommand(String locale) {
        this.locale = locale;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        boolean isRussianLanguage = RUSSIAN.equals(locale);
        if (isRussianLanguage) {
            session.setAttribute(LOCALE, RUSSIAN_LOCALE);
        } else {
            session.setAttribute(LOCALE, ENGLISH_LOCALE);
        }

        String page = (String) session.getAttribute("currentPage");

        return page == null ?
                CommandResult.forward(LOGIN_PAGE) :
                CommandResult.forward(page);
    }
}
