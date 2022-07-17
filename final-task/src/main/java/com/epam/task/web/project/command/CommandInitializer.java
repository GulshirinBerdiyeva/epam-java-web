package com.epam.task.web.project.command;

import com.epam.task.web.project.converter.CurrencyConverter;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.data.MusicFileReader;
import com.epam.task.web.project.data.MusicFileWriter;
import com.epam.task.web.project.entity.*;
import com.epam.task.web.project.locale.DateTimeLocalizer;
import com.epam.task.web.project.locale.Localizer;
import com.epam.task.web.project.parser.MusicParser;
import com.epam.task.web.project.parser.Parser;
import com.epam.task.web.project.service.*;
import com.epam.task.web.project.validator.*;
import org.apache.commons.fileupload.FileItem;

public class CommandInitializer {

    private final Validator fullNameValidator = new FullNameValidator();
    private final Validator passwordValidator = new PasswordValidator();
    private final Validator fileNameValidator = new FileNameValidator();
    private final Validator commentValidator = new CommentValidator();
    private final Validator titleValidator = new TitleValidator();
    private final Validator discountValidator = new DiscountValidator();
    private final Validator doubleNumberValidator = new DoubleNumberValidator();
    private final Validator numberValidator = new NumberValidator();

    private final Parser<FileItem> musicParser = new MusicParser();
    private final MusicFileReader musicFileReader = new MusicFileReader();
    private final MusicFileWriter musicFileWriter = new MusicFileWriter();
    private final CurrencyConverter currencyConverter = new CurrencyConverter();
    private final Localizer dateTimeLocalizer = new DateTimeLocalizer();

    private final DaoHelperFactory daoHelperFactory = new DaoHelperFactory();

    private final Service<User> userService = new UserService(daoHelperFactory);
    private final Service<Music> musicService = new MusicService(daoHelperFactory);
    private final Service<MusicOrder> musicOrderService = new MusicOrderService(daoHelperFactory);
    private final Service<Playlist> playlistService = new PlaylistService(daoHelperFactory);
    private final Service<Album> albumService = new AlbumService(daoHelperFactory);
    private final Service<Comment> commentService = new CommentService(daoHelperFactory);


    public Validator getFullNameValidator() {
        return fullNameValidator;
    }

    public Validator getPasswordValidator() {
        return passwordValidator;
    }

    public Validator getFileNameValidator() {
        return fileNameValidator;
    }

    public Validator getCommentValidator() {
        return commentValidator;
    }

    public Validator getTitleValidator() {
        return titleValidator;
    }

    public Validator getDiscountValidator() {
        return discountValidator;
    }

    public Validator getDoubleNumberValidator() {
        return doubleNumberValidator;
    }

    public Validator getNumberValidator() {
        return numberValidator;
    }

    public Parser<FileItem> getMusicParser() {
        return musicParser;
    }

    public MusicFileReader getMusicFileReader() {
        return musicFileReader;
    }

    public MusicFileWriter getMusicFileWriter() {
        return musicFileWriter;
    }

    public CurrencyConverter getCurrencyConverter() {
        return currencyConverter;
    }

    public Localizer getDateTimeLocalizer() {
        return dateTimeLocalizer;
    }

    public DaoHelperFactory getDaoHelperFactory() {
        return daoHelperFactory;
    }

    public Service<User> getUserService() {
        return userService;
    }

    public Service<Music> getMusicService() {
        return musicService;
    }

    public Service<MusicOrder> getMusicOrderService() {
        return musicOrderService;
    }

    public Service<Playlist> getPlaylistService() {
        return playlistService;
    }

    public Service<Album> getAlbumService() {
        return albumService;
    }

    public Service<Comment> getCommentService() {
        return commentService;
    }
}
