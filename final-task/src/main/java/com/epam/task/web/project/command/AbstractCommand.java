package com.epam.task.web.project.command;

import com.epam.task.web.project.converter.CurrencyConverter;
import com.epam.task.web.project.data.MusicFileReader;
import com.epam.task.web.project.data.MusicFileWriter;
import com.epam.task.web.project.entity.*;
import com.epam.task.web.project.locale.Localizer;
import com.epam.task.web.project.parser.Parser;
import com.epam.task.web.project.service.Service;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.Validator;
import org.apache.commons.lang.NotImplementedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand implements Command {

    private static final CommandInitializer COMMAND_INITIALIZER = new CommandInitializer();

    protected Validator getValidator(String type) throws CommandException {
        switch(type) {
            case "fullName":
                return COMMAND_INITIALIZER.getFullNameValidator();
            case "password":
                return COMMAND_INITIALIZER.getPasswordValidator();
            case "comment":
                return COMMAND_INITIALIZER.getCommentValidator();
            case "title":
                return COMMAND_INITIALIZER.getTitleValidator();
            case "discount":
                return COMMAND_INITIALIZER.getDiscountValidator();
            case "number":
                return COMMAND_INITIALIZER.getNumberValidator();
            case "doubleNumber":
                return COMMAND_INITIALIZER.getDoubleNumberValidator();
            case "fileName":
                return COMMAND_INITIALIZER.getFileNameValidator();
            default:
                throw new CommandException("Unknown type of validator! \"" + type + "\"");
        }
    }

    protected <T extends Entity> Service<T> getService(Class<T> type) throws CommandException {
        if (type.equals(User.class)) {
            return (Service<T>) COMMAND_INITIALIZER.getUserService();

        } else if(type.equals(Music.class)) {
            return (Service<T>) COMMAND_INITIALIZER.getMusicService();

        } else if(type.equals(MusicOrder.class)) {
            return (Service<T>) COMMAND_INITIALIZER.getMusicOrderService();

        } else if(type.equals(Playlist.class)) {
            return (Service<T>) COMMAND_INITIALIZER.getPlaylistService();

        } else if(type.equals(Album.class)) {
            return (Service<T>) COMMAND_INITIALIZER.getAlbumService();

        } else if(type.equals(Comment.class)) {
            return (Service<T>) COMMAND_INITIALIZER.getCommentService();

        } else {
            throw new CommandException("Unknown type of Service! \"" + type + "\"");
        }
    }

    protected Parser getParser(String type) throws CommandException {
        switch (type) {
            case "music":
                return COMMAND_INITIALIZER.getMusicParser();
            default:
                throw new CommandException("Unknown type of parser! \"" + type + "\"");
        }
    }

    protected Localizer getLocalizer(String type) throws CommandException {
        switch (type) {
            case "dateTime":
                return COMMAND_INITIALIZER.getDateTimeLocalizer();
            default:
                throw new CommandException("Unknown type of localizer! \"" + type + "\"");
        }
    }

    protected MusicFileReader getMusicFileReader() {
        return COMMAND_INITIALIZER.getMusicFileReader();
    }

    protected MusicFileWriter getMusicFileWriter() {
        return COMMAND_INITIALIZER.getMusicFileWriter();
    }

    protected CurrencyConverter getCurrencyConverter() {
        return COMMAND_INITIALIZER.getCurrencyConverter();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        throw new NotImplementedException();
    }

}
