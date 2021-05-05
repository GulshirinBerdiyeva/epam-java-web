package com.epam.task.web.project.command;

import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.service.*;
import com.epam.task.web.project.validator.InputParameterValidator;

public class CommandFactory {

    private static final String REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";
    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";
    private static final String PROFILE_PAGE = "/WEB-INF/view/profile.jsp";
    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";
    private static final String ADD_NEW_MUSIC_PAGE = "/WEB-INF/view/addNewMusic.jsp";
    private static final String CREATE_ALBUM_PAGE = "/WEB-INF/view/createAlbum.jsp";

    private final UserService userService;
    private final MusicService musicService;
    private final MusicOrderService musicOrderService;
    private final PlaylistService playlistService;
    private final AlbumService albumService;
    private final CommentService commentService;
    private InputParameterValidator validator;

    public CommandFactory(DaoHelperFactory daoHelperFactory, InputParameterValidator validator) {
        this.userService = new UserService(daoHelperFactory);
        this.musicService = new MusicService(daoHelperFactory);
        this.musicOrderService = new MusicOrderService(daoHelperFactory);
        this.playlistService = new PlaylistService(daoHelperFactory);
        this.albumService = new AlbumService(daoHelperFactory);
        this.commentService = new CommentService(daoHelperFactory);
        this.validator = validator;
    }

    public Command create(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid command for this user!");
        }

        switch (type){
            case "changeLanguage":
                return new ChangeLanguageCommand(validator);
            case "signUpPage":
                return new ShowPageCommand(REGISTRATION_PAGE);
            case "signIn":
                return new SignInCommand(userService, validator);
            case "login":
                return new LoginCommand(userService);
            case "logout":
                return new LogoutCommand();
            case "main":
                return new MainCommand(musicService);
            case "getMusic":
                return new GetMusicCommand(musicService);
            case "getResource":
                return new GetResourceCommand(validator);
            case "searchPage":
                return new ShowPageCommand(SEARCH_PAGE);
            case "searchMusic":
                return new SearchMusicCommand(musicService, validator);
            case "selectMusic":
                return new SelectMusicCommand(musicService);
            case "purchasePage":
                return new ShowPageCommand(PURCHASE_PAGE);
            case "buy":
                return new BuyPurchaseCommand(userService, musicOrderService, playlistService);
            case "confirmPurchase":
                return new ConfirmPurchaseCommand(musicOrderService, playlistService);
            case "cancelPurchase":
                return new CancelPurchaseCommand(musicOrderService);
            case"editMusic":
                return new EditMusicCommand(musicService);
            case"editPrice":
                return new EditPriceCommand(musicService, validator);
            case"deleteMusic":
                return new DeleteMusicCommand(musicService, userService);
            case "playlist":
                return new PlaylistCommand(playlistService);
            case "albums":
                return new AlbumsCommand(albumService);
            case "createAlbumPage":
                return new ShowPageCommand(CREATE_ALBUM_PAGE);
            case "albumMusics":
                return new AlbumMusicsCommand(albumService, validator);
            case "addAlbum":
                return new AddAlbumCommand(albumService, validator);
            case "addMusic":
                return new AddMusicCommand(musicService, validator);
            case "addMusicPage":
                return new ShowPageCommand(ADD_NEW_MUSIC_PAGE);
            case "comments":
                return new CommentsCommand(commentService);
            case "createComment":
                return new CreateCommentCommand(commentService, validator);
            case "profilePage":
                return new ShowPageCommand(PROFILE_PAGE);
            case "fillBalance":
                return new FillBalanceCommand(userService, validator);
            case "clients":
                return new ClientsCommand(userService);
            case "applyDiscount":
                return new ApplyDiscountCommand(userService, validator);
            default:
                throw new IllegalArgumentException("Unknown type of command! \"" + type + "\"");
        }

    }

}
