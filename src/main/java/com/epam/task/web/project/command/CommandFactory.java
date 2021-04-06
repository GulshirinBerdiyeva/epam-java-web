package com.epam.task.web.project.command;

import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.service.ServiceFactory;

public class CommandFactory {

    private static final DaoHelperFactory DAO_HELPER_FACTORY = new DaoHelperFactory();
    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory(DAO_HELPER_FACTORY);

    private static final String ALBUMS_PAGE = "/WEB-INF/view/fragments/albums.jsp";
    private static final String PLAYLIST_PAGE = "/WEB-INF/view/fragments/playlist.jsp";
    private static final String SEARCH_PAGE = "/WEB-INF/view/fragments/search.jsp";
    private static final String PURCHASE_PAGE = "/WEB-INF/view/fragments/purchase.jsp";
    private static final String EDIT_PAGE = "/WEB-INF/view/fragments/edit.jsp";
    private static final String PAYED_PAGE = "/WEB-INF/view/fragments/payed.jsp";

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
                return new MainCommand(SERVICE_FACTORY);
            case "search":
                return new ShowPageCommand(SEARCH_PAGE);
            case "selectMusic":
                return new SelectMusicCommand(SERVICE_FACTORY);
            case "buy":
                return new BuyCommand(SERVICE_FACTORY);
            case "musicOrder":
                return new MusicOrderCommand();
            case "purchasePage":
                return new ShowPageCommand(PURCHASE_PAGE);
            case "confirm":
                return new ConfirmCommand(SERVICE_FACTORY);
            case "payedPage":
                return new ShowPageCommand(PAYED_PAGE);
            case "cancel":
                return new CancelCommand(SERVICE_FACTORY);
            case "playlist":
                return new PlaylistCommand(SERVICE_FACTORY);
            case "playlistPage":
                return new ShowPageCommand(PLAYLIST_PAGE);
            case "albums":
                return new AlbumsCommand(SERVICE_FACTORY);
            case "albumsPage":
                return new ShowPageCommand(ALBUMS_PAGE);
            case "editPage":
                return new ShowPageCommand(EDIT_PAGE);
            case "edit":
                return new BuyCommand(SERVICE_FACTORY);
            case "createAlbum":
                return new CreateAlbumCommand();
            default:
                throw new IllegalArgumentException("Unknown type of command!");
        }
    }

}
