package com.epam.task.web.project.data;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MusicFileWriter {

    private static final String IMAGE_FILE = "imageFile";
    private static final String FILE_NAME = "appResources.properties";
    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";

    public void write(List<FileItem> musicData) throws DataException {
        try {
            for (FileItem item : musicData) {
                if (!item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String directoryName;
                    String fileName = item.getName();

                    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
                    Properties properties = new Properties();
                    properties.load(inputStream);

                    if (IMAGE_FILE.equals(fieldName)) {
                        directoryName = properties.getProperty(IMAGES_DIRECTORY);
                    } else {
                        directoryName = properties.getProperty(MUSICS_DIRECTORY);
                    }

                    item.write(new File(directoryName + File.separator + fileName));
                }
            }
        } catch (Exception e) {
            throw new DataException(e.getMessage(), e);
        }

    }

}
