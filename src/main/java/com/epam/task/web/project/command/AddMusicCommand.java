package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.extractor.ExtractException;
import com.epam.task.web.project.extractor.MusicExtractor;
import com.epam.task.web.project.extractor.PropertiesExtractor;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class AddMusicCommand implements Command {

    private final MusicService musicService;
    private InputParameterValidator inputParameterValidator = new InputParameterValidator();

    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";
    private static final String FILE_NAME = "appResources.properties";
    private static final String IMAGE_FILE = "imageFile";
    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";

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

            saveFiles(musicData);

            MusicExtractor musicExtractor = new MusicExtractor();
            Map<String, String> musicValues = (Map<String, String>) musicExtractor.extract(musicData);

            Music music = musicService.createMusic(musicValues);

            Optional<Music> optionalMusic = musicService.findMusicByArtistAndTitle(music.getArtist(), music.getTitle());
            if (optionalMusic.isPresent()) {
                Music oldMusic = optionalMusic.get();
                musicService.updateMusic(oldMusic);
            } else {
                musicService.saveMusic(music);
            }

            return CommandResult.redirect(MAIN_COMMAND);
        } catch (FileUploadException | ExtractException e) {
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

    private void saveFiles(List<FileItem> musicData) throws ServiceException {
        try {
            for (FileItem item : musicData) {
                if (!item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String directoryName;
                    String fileName = item.getName();

                    PropertiesExtractor propertiesExtractor = new PropertiesExtractor();
                    Properties properties = (Properties) propertiesExtractor.extract(FILE_NAME);

                    if (IMAGE_FILE.equals(fieldName)) {
                        directoryName = properties.getProperty(IMAGES_DIRECTORY);
                    } else {
                        directoryName = properties.getProperty(MUSICS_DIRECTORY);
                    }

                    item.write(new File(directoryName + File.separator + fileName));
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}

