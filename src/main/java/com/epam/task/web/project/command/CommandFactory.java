package com.epam.task.web.project.command;

import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.service.ServiceFactory;

public class CommandFactory {

    private static final DaoHelperFactory DAO_HELPER_FACTORY = new DaoHelperFactory();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory(DAO_HELPER_FACTORY);

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String ALBUMS_PAGE = "/WEB-INF/view/fragments/albums.jsp";
    private static final String PLAYLIST_PAGE = "/WEB-INF/view/fragments/playlist.jsp";
    private static final String SEARCH_PAGE = "/WEB-INF/view/fragments/search.jsp";

    private static final String ENGLISH = "english";
    private static final String FRANCE = "france";
    private static final String RUSSIAN = "russian";

    public static Command create(String type) {
        switch (type){
            case "english":
                return new ChangeLocalCommand(ENGLISH);
            case "france":
                return new ChangeLocalCommand(FRANCE);
            case "russian":
                return new ChangeLocalCommand(RUSSIAN);
            case "login":
                return new LoginCommand(SERVICE_FACTORY);
            case "logout":
                return new LogoutCommand();
            case "main":
                return new ShowPageCommand(MAIN_PAGE);
            case "albums":
                return new ShowPageCommand(ALBUMS_PAGE);
            case "playlist":
                return new ShowPageCommand(PLAYLIST_PAGE);
            case "search":
                return new ShowPageCommand(SEARCH_PAGE);
            case "purchase":
                return new PurchaseCommand(SERVICE_FACTORY);
            case "buy":
                return new BuyCommand(SERVICE_FACTORY);
            case "createAlbum":
                return new CreateAlbumCommand();
            default:
                throw new IllegalArgumentException("Unknown type of command!");
        }
    }

}
