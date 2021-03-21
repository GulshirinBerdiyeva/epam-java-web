package com.epam.task.web.project.command;

import com.epam.task.web.project.service.UserService;

public class CommandFactory {
    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String ALBUMS_PAGE = "/WEB-INF/view/fragments/albums.jsp";
    private static final String PLAYLISTS_PAGE = "/WEB-INF/view/fragments/playlists.jsp";
    private static final String SEARCH_PAGE = "/WEB-INF/view/fragments/search.jsp";

    private static final String ENGLISH = "english";
    private static final String RUSSIAN = "russian";

    public static Command create(String type){
        switch (type){
            case "login":
                return new LoginCommand(new UserService());
            case "logout":
                return new ShowPageCommand(LOGIN_PAGE);
            case "main":
                return new ShowPageCommand(MAIN_PAGE);
            case "albums":
                return new ShowPageCommand(ALBUMS_PAGE);
            case "playlists":
                return new ShowPageCommand(PLAYLISTS_PAGE);
            case "search":
                return new ShowPageCommand(SEARCH_PAGE);
            case "english":
                return new ChangeLocaleCommand(ENGLISH);
            case "russian":
                return new ChangeLocaleCommand(RUSSIAN);
            default:
                throw new IllegalArgumentException("Unknown type of command '" + type + "'");
        }
    }
}
