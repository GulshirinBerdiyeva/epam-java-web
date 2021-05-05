package com.epam.task.web.project.command;

import com.epam.task.web.project.data.DataException;
import com.epam.task.web.project.data.MusicFileWriter;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.parser.MusicParser;
import com.epam.task.web.project.parser.ParserException;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class AddMusicCommand implements Command {

    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";
    private static final String PRICE = "price";

    private static final String ADD_NEW_MUSIC_PAGE = "/WEB-INF/view/addNewMusic.jsp";
    private static final String MAIN_COMMAND = "?command=main";

    private final MusicService musicService;
    private InputParameterValidator validator;

    public AddMusicCommand(MusicService musicService, InputParameterValidator validator) {
        this.musicService = musicService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            if (request.getContentType() == null) {
                throw new NullPointerException("Parameter is NULL...");
            }

            List<FileItem> musicData = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            boolean isValid = isValid(musicData);
            if (!isValid) {
                request.setAttribute(EMPTY_INPUT_PARAMETERS, true);
                return CommandResult.forward(ADD_NEW_MUSIC_PAGE);
            }

            MusicFileWriter dataWriter = new MusicFileWriter();
            dataWriter.write(musicData);

            MusicParser parser = new MusicParser();
            Map<String, String> musicValues = parser.extractInputParameters(musicData);

            Music music = musicService.createMusic(musicValues);
            String artist = music.getArtist();
            String title = music.getTitle();

            Optional<Music> optionalMusic = musicService.findMusicByArtistAndTitle(artist, title);
            if (optionalMusic.isPresent()) {
                Music oldMusic = optionalMusic.get();
                musicService.updateMusic(oldMusic);
            } else {
                musicService.saveMusic(music);
            }

            return CommandResult.redirect(MAIN_COMMAND);
        } catch (FileUploadException | DataException | ParserException e) {
            throw new ServiceException(e);
        }
    }

    private boolean isValid(List<FileItem> musicData) {
        String inputParameter;
        boolean isNumber;
        for (FileItem item : musicData) {

            if (item.isFormField()) {
                inputParameter = item.getString();
                isNumber = PRICE.equals(item.getFieldName());
            } else {
                inputParameter = item.getName();
                isNumber = false;
            }

            boolean isValid =   isNumber ? validator.isValidNumber(inputParameter) : validator.isValidString(inputParameter);
            if (!isValid) {
                return false;
            }

        }

        return true;
    }

}

