package com.epam.task.web.project.command;

import com.epam.task.web.project.extractor.ExtractException;
import com.epam.task.web.project.extractor.PropertiesExtractor;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class GetResourceCommand implements Command{

    private static final String FILE_NAME = "appResources.properties";
    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";
    private static final String IMAGE_FILE_NAME = "imageFileName";
    private static final String AUDIO_FILE_NAME = "audioFileName";
    private static final String IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String AUDIO_CONTENT_TYPE = "audio/mp3";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String imageFileName = request.getParameter(IMAGE_FILE_NAME);
        String audioFileName = request.getParameter(AUDIO_FILE_NAME);

        try {
            PropertiesExtractor propertiesExtractor = new PropertiesExtractor();
            Properties properties = (Properties) propertiesExtractor.extract(FILE_NAME);
            File file;
            String directory;

            if (imageFileName != null) {
                response.setContentType(IMAGE_CONTENT_TYPE);
                directory = properties.getProperty(IMAGES_DIRECTORY);
                file = new File(directory + File.separator + imageFileName);
            } else {
                response.setContentType(AUDIO_CONTENT_TYPE);
                directory = properties.getProperty(MUSICS_DIRECTORY);
                file = new File(directory + File.separator + audioFileName);
            }

            byte[] fileContent = Files.readAllBytes(file.toPath());
            response.setContentLength(fileContent.length);
            response.getOutputStream().write(fileContent);

            String page = (String) request.getSession().getAttribute("currentPage");
            return CommandResult.forward(page);
        } catch (ExtractException | IOException e) {
            throw new ServiceException(e);
        }

    }
}
