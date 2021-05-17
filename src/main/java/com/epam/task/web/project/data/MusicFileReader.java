package com.epam.task.web.project.data;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public class MusicFileReader {

    private static final String IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String AUDIO_CONTENT_TYPE = "audio/mpeg";
    private static final String ICONS_DIRECTORY = "iconsDirectory";
    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";

    private Properties initProperties(String dataFileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dataFileName);
        Properties properties = new Properties();
        properties.load(inputStream);

        return properties;
    }

    private void writeBytesIntoResponse(String directory, String fileName, HttpServletResponse response) throws IOException {
        File file = new File(directory + File.separator + fileName);
        byte[] fileContent = Files.readAllBytes(file.toPath());

        response.setContentLength(fileContent.length);
        response.getOutputStream().write(fileContent);
    }

    public void readIconFile(String dataFileName, String fileName, HttpServletResponse response) throws DataException {
        try {
            Properties properties = initProperties(dataFileName);

            String directory = properties.getProperty(ICONS_DIRECTORY);
            response.setContentType(IMAGE_CONTENT_TYPE);

            writeBytesIntoResponse(directory, fileName, response);
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    public void readImageFile(String dataFileName, String fileName, HttpServletResponse response) throws DataException {
        try {
            Properties properties = initProperties(dataFileName);

            String directory = properties.getProperty(IMAGES_DIRECTORY);
            response.setContentType(IMAGE_CONTENT_TYPE);

            writeBytesIntoResponse(directory, fileName, response);
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    public void readAudioFile(String dataFileName, String fileName, HttpServletResponse response) throws DataException {
        try {
            Properties properties = initProperties(dataFileName);

            String directory = properties.getProperty(MUSICS_DIRECTORY);
            response.setContentType(AUDIO_CONTENT_TYPE);

            writeBytesIntoResponse(directory, fileName, response);
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

}