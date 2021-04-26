package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
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

    private final MusicService musicService;
    private InputParameterValidator inputParameterValidator = new InputParameterValidator();

    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";
    private static final String ADD_NEW_MUSIC_PAGE = "/WEB-INF/view/addNewMusic.jsp";
    private static final String MAIN_COMMAND = "?command=main";

    public AddMusicCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            List<FileItem> musicData = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            boolean isValid = isValid(musicData);
            if (!isValid) {
                request.setAttribute(EMPTY_INPUT_PARAMETERS, true);
                return CommandResult.forward(ADD_NEW_MUSIC_PAGE);
            }

            musicService.saveFiles(musicData);
            Map<String, String> musicValues = musicService.extractInputParameters(musicData);

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
        } catch (FileUploadException e) {
            throw new ServiceException(e);
        }
    }

    private boolean isValid(List<FileItem> musicData) {
        for (FileItem item : musicData) {
            String inputParameter;

            if (item.isFormField()) {
                inputParameter = item.getString();
            } else {
                inputParameter = item.getName();
            }

            boolean isValid = inputParameterValidator.isValid(inputParameter);
            if (!isValid) {
                return false;
            }
        }

        return true;
    }

}

