package com.epam.task.web.project.data;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public class MusicFileReader {

    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";
    private static final String IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String AUDIO_CONTENT_TYPE = "audio/mp3";

    public void read(String fileName, String imageFileName, String audioFileName, HttpServletResponse response) throws DataException {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
            Properties properties = new Properties();
            properties.load(inputStream);

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

        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }

    }

}
