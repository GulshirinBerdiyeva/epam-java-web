package com.epam.task.web.project.command;

import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocalCommand implements Command{

    private final String local;

    private static final String FRANCE = "france";
    private static final String RUSSIAN = "russian";

    private static final String ENGLISH_LOCAL = "EN";
    private static final String FRANCE_LOCAL = "FR";
    private static final String RUSSIAN_LOCAL = "RU";

    private static final String LOCAL = "local";
    private static final String CURRENT_PAGE = "currentPage";

    private static final String LOGIN_PAGE = "/index.jsp";

    public ChangeLocalCommand(String local) {
        this.local = local;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(true);

        switch (local) {
            case FRANCE:
                session.setAttribute(LOCAL, FRANCE_LOCAL);
                break;
            case RUSSIAN:
                session.setAttribute(LOCAL, RUSSIAN_LOCAL);
                break;
            default:
                session.setAttribute(LOCAL, ENGLISH_LOCAL);
        }

        String page = (String) session.getAttribute(CURRENT_PAGE);

        return page == null ?
                CommandResult.forward(LOGIN_PAGE) : CommandResult.forward(page);
    }

}
