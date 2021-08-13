package com.epam.task.web.project.command;

import com.epam.task.web.project.data.DataException;
import com.epam.task.web.project.data.MusicFileWriter;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.parser.MusicParser;
import com.epam.task.web.project.parser.ParserException;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddMusicCommand extends AbstractCommand {

    private static final Logger LOGGER = LogManager.getLogger(AddMusicCommand.class);

    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";
    private static final String PRICE = "price";
    private static final String ARTIST = "artist";
    private static final String TITLE = "title";
    private static final String FULL_NAME = "fullName";
    private static final String DOUBLE_NUMBER = "doubleNumber";
    private static final String FILE_NAME = "fileName";
    private static final String MUSIC = "music";

    private static final String ADD_NEW_MUSIC_PAGE = "/WEB-INF/view/addNewMusic.jsp";
    private static final String MAIN_COMMAND = "?command=mainPage";

    private static final AtomicReference<AddMusicCommand> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private final FullNameValidator fullNameValidator;
    private final TitleValidator titleValidator;
    private final DoubleNumberValidator doubleNumberValidator;
    private final FileNameValidator fileNameValidator;
    private final MusicService musicService;
    private final MusicParser musicParser;
    private final MusicFileWriter musicFileWriter;

    private AddMusicCommand() throws CommandException {
        this.fullNameValidator = (FullNameValidator) getValidator(FULL_NAME);
        this.titleValidator = (TitleValidator) getValidator(TITLE);
        this.doubleNumberValidator = (DoubleNumberValidator) getValidator(DOUBLE_NUMBER);
        this.fileNameValidator = (FileNameValidator) getValidator(FILE_NAME);
        this.musicService = (MusicService) getService(Music.class);
        this.musicParser = (MusicParser) getParser(MUSIC);
        this.musicFileWriter = getMusicFileWriter();
    }

    public static AddMusicCommand getInstance() throws CommandException {
        if (!IS_INSTANCE_CREATED.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    AddMusicCommand addMusicCommand = new AddMusicCommand();

                    INSTANCE.set(addMusicCommand);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created AddMusicCommand instance");
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            List<FileItem> musicData = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            if (!isValid(musicData)) {
                request.setAttribute(EMPTY_INPUT_PARAMETERS, true);
                return CommandResult.forward(ADD_NEW_MUSIC_PAGE);
            }

            musicFileWriter.write(musicData);

            Map<String, String> musicValues = (Map<String, String>) musicParser.parse(musicData);

            Music music = musicService.createMusic(musicValues);

            String artist = music.getArtist();
            String title = music.getTitle();

            Optional<Music> optionalMusic = musicService.getMusicByArtistAndTitle(artist, title);

            if (optionalMusic.isPresent()) {
                musicService.updateMusic(music);

            } else {
                musicService.save(music);
            }

            return CommandResult.redirect(MAIN_COMMAND);

        } catch (FileUploadException | DataException | ParserException e) {
            throw new ServiceException(e.getMessage(), e);
        }

    }

    private boolean isValid(List<FileItem> musicData) throws ServiceException {
        if (musicData == null || musicData.isEmpty()) {
            return false;
        }

        try {
            for (FileItem item : musicData) {
                boolean isPrice = false;
                boolean isTitle = false;
                boolean isArtist = false;
                boolean isFileName = false;

                String parameterName = (item.getFieldName() != null) ? item.getFieldName() : "";
                String parameterValue;

                if (item.isFormField()) {
                    parameterValue = item.getString("UTF-8");

                    switch (parameterName) {
                        case ARTIST:
                            isArtist = true;
                            break;
                        case TITLE:
                            isTitle = true;
                            break;
                        case PRICE:
                            isPrice = true;
                            break;
                        default:
                            throw new ServiceException("Unknown type of parameter name! \"" + parameterName + "\"");
                    }

                } else {
                    parameterValue = item.getName();
                    isFileName = true;
                }

                boolean isValid = false;

                if (isArtist) {
                    isValid = fullNameValidator.isValid(parameterValue);
                }
                if (isTitle) {
                    isValid = titleValidator.isValid(parameterValue);
                }
                if (isPrice) {
                    isValid = doubleNumberValidator.isValid(parameterValue);
                }
                if (isFileName) {
                    isValid = fileNameValidator.isValid(parameterValue, parameterName);
                }

                if (!isValid) {
                    return false;
                }
            }

        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return true;
    }
}