package com.epam.task.web.project.command;

import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.service.*;

public class CommandFactory {

    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";
    private static final String PROFILE_PAGE = "/WEB-INF/view/profile.jsp";
    private static final String PURCHASE_PAGE = "/WEB-INF/view/fragments/purchase.jsp";
    private static final String ADD_NEW_MUSIC_PAGE = "/WEB-INF/view/addNewMusic.jsp";
    private static final String CREATE_ALBUM_PAGE = "/WEB-INF/view/createAlbum.jsp";

    public static Command create(String type) {
        switch (type){
            case "changeLanguage":
                return new ChangeLanguageCommand();
            case "login":
                return new LoginCommand(new UserService(new DaoHelperFactory()));
            case "signUp":
                return new SignUpCommand();
            case "signIn":
                return new SignInCommand(new UserService(new DaoHelperFactory()));
            case "logout":
                return new LogoutCommand();
            case "main":
                return new MainCommand(new MusicService(new DaoHelperFactory()));
            case "search":
                return new ShowPageCommand(SEARCH_PAGE);
            case "searchMusic":
                return new SearchMusicCommand(new MusicService(new DaoHelperFactory()));
            case "selectMusic":
                return new SelectMusicCommand(new MusicService(new DaoHelperFactory()));
            case "purchasePage":
                return new ShowPageCommand(PURCHASE_PAGE);
            case "buy":
                return new BuyPurchaseCommand(new UserService(new DaoHelperFactory()),
                                              new MusicOrderService(new DaoHelperFactory()),
                                              new PlaylistService(new DaoHelperFactory()));
            case "confirmPurchase":
                return new ConfirmPurchaseCommand(new MusicOrderService(new DaoHelperFactory()),
                                                  new PlaylistService(new DaoHelperFactory()));
            case "cancelPurchase":
                return new CancelPurchaseCommand(new MusicOrderService(new DaoHelperFactory()));
            case"editMusic":
                return new EditMusicCommand(new MusicService(new DaoHelperFactory()));
            case"editPrice":
                return new EditPriceCommand(new MusicService(new DaoHelperFactory()));
            case"deleteMusic":
                return new DeleteMusicCommand(new MusicService(new DaoHelperFactory()),
                                              new UserService(new DaoHelperFactory()));
            case "playlist":
                return new PlaylistCommand(new PlaylistService(new DaoHelperFactory()));
            case "albums":
                return new AlbumsCommand(new AlbumService(new DaoHelperFactory()));
            case "createAlbum":
                return new ShowPageCommand(CREATE_ALBUM_PAGE);
            case "albumMusics":
                return new AlbumMusicsCommand(new AlbumService(new DaoHelperFactory()));
            case "submitAlbum":
                return new SubmitAlbumCommand(new AlbumService(new DaoHelperFactory()));
            case "addMusic":
                return new AddMusicCommand(new MusicService(new DaoHelperFactory()));
            case "addNewMusic":
                return new ShowPageCommand(ADD_NEW_MUSIC_PAGE);
            case "comments":
                return new CommentsCommand(new CommentService(new DaoHelperFactory()));
            case "createComment":
                return new CreateCommentCommand(new CommentService(new DaoHelperFactory()));
            case "profile":
                return new ShowPageCommand(PROFILE_PAGE);
            case "topUpBalance":
                return new TopUpBalanceCommand(new UserService(new DaoHelperFactory()));
            case "clients":
                return new ClientsCommand(new UserService(new DaoHelperFactory()));
            case "applyDiscount":
                return new ApplyDiscountCommand(new UserService(new DaoHelperFactory()));
            default:
                throw new IllegalArgumentException("Unknown type of command!");
        }
    }

}
