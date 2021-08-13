package com.epam.task.web.project.data;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class MusicFileReader {

    private static final String MUSIC = "music";
    private static final String ICON = "icon";
    private static final String IMAGE = "image";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";
    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String ICONS_DIRECTORY = "iconsDirectory";
    private static final String IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String AUDIO_CONTENT_TYPE = "audio/mpeg";

    private final PropertiesInitializer propertiesInitializer = new PropertiesInitializer();

    public void read(String dataFileName, String fileName, String type, HttpServletResponse response) throws DataException {
        try {
            Properties properties = propertiesInitializer.init(dataFileName);
            String directory;

            switch (type) {
                case MUSIC:
                    directory = properties.getProperty(MUSICS_DIRECTORY);
                    response.setContentType(AUDIO_CONTENT_TYPE);
                    break;
                case IMAGE:
                    directory = properties.getProperty(IMAGES_DIRECTORY);
                    response.setContentType(IMAGE_CONTENT_TYPE);
                    break;
                case ICON:
                    directory = properties.getProperty(ICONS_DIRECTORY);
                    response.setContentType(IMAGE_CONTENT_TYPE);
                    break;
                default:
                    throw new DataException("Unknown type of required resource! \"" + type + "\"");
            }

            writeBytesIntoResponse(directory, fileName, response);

        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    private void writeBytesIntoResponse(String directory, String fileName, HttpServletResponse response) throws IOException {
        File file = new File(directory + File.separator + fileName);

        byte[] fileContent = Files.readAllBytes(file.toPath());

        response.setContentLength(fileContent.length);
        response.getOutputStream().write(fileContent);
    }

}
