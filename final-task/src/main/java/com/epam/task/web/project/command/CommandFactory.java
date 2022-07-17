package com.epam.task.web.project.command;

public class CommandFactory {

    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String REGISTRATION_PAGE = "/WEB-INF/view/registration.jsp";
    private static final String ADD_NEW_MUSIC_PAGE = "/WEB-INF/view/addNewMusic.jsp";

    public Command create(String type) throws CommandException {
        if (type == null) {
            throw new NullPointerException();
        }

        switch (type){
            case "changeLanguage":
                return ChangeLanguageCommand.getInstance();
            case "loginPage":
                ShowPageCommand showPageCommand = ShowPageCommand.getInstance();
                showPageCommand.setPage(LOGIN_PAGE);
                return showPageCommand;
            case "login":
                return LoginCommand.getInstance();
            case "logout":
                return LogoutCommand.getInstance();
            case "signUpPage":
                showPageCommand = ShowPageCommand.getInstance();
                showPageCommand.setPage(REGISTRATION_PAGE);
                return showPageCommand;
            case "signIn":
                return SignInCommand.getInstance();
            case "mainPage":
                return MainCommand.getInstance();
            case "playlistPage":
                return PlaylistCommand.getInstance();
            case "albumsPage":
                return AlbumsCommand.getInstance();
            case "createAlbumPage":
                return CreateAlbumPageCommand.getInstance();
            case "addMusicPage":
                showPageCommand = ShowPageCommand.getInstance();
                showPageCommand.setPage(ADD_NEW_MUSIC_PAGE);
                return showPageCommand;
            case "profilePage":
                return ProfilePageCommand.getInstance();
            case "clientsPage":
                return ClientsCommand.getInstance();
            case "getMusic":
                return GetMusicCommand.getInstance();
            case "getResource":
                return GetResourceCommand.getInstance();
            case "searchMusic":
                return SearchMusicCommand.getInstance();
            case "selectMusic":
                return SelectMusicCommand.getInstance();
            case "purchasePage":
                return PurchasePageCommand.getInstance();
            case "buy":
                return BuyPurchaseCommand.getInstance();
            case "confirmPurchase":
                return ConfirmPurchaseCommand.getInstance();
            case "cancelPurchase":
                return CancelPurchaseCommand.getInstance();
            case"editPrice":
                return EditPriceCommand.getInstance();
            case"deleteMusic":
            case"confirmDelete":
            case"cancelDelete":
                return DeleteMusicCommand.getInstance();
            case "albumMusics":
                return AlbumMusicsCommand.getInstance();
            case "addAlbum":
                return AddAlbumCommand.getInstance();
            case "addMusic":
                return AddMusicCommand.getInstance();
            case "createComment":
                return CreateCommentCommand.getInstance();
            case "fillBalance":
                return FillBalanceCommand.getInstance();
            case "applyDiscount":
                return ApplyDiscountCommand.getInstance();
            default:
                throw new IllegalArgumentException("Unknown type of command! \"" + type + "\"");
        }

    }

}
