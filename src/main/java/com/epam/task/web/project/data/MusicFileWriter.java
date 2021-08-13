package com.epam.task.web.project.data;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MusicFileWriter {

    private static final String FILE_NAME = "appResources.properties";
    private static final String MUSICS_DIRECTORY = "musicsDirectory";
    private static final String IMAGES_DIRECTORY = "imagesDirectory";
    private static final String AUDIO_FILE = "audioFile";
    private static final String IMAGE_FILE = "imageFile";

    private final PropertiesInitializer propertiesInitializer = new PropertiesInitializer();

    private static final int SEMAPHORE_PERMITS = 10;
    private final Semaphore semaphore = new Semaphore(SEMAPHORE_PERMITS);
    private final Lock lock = new ReentrantLock();

    public void write(List<FileItem> musicData) throws DataException {
        try {
            semaphore.acquire();
            lock.lock();

            for (FileItem item : musicData) {

                if (!item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();

                    Properties properties = propertiesInitializer.init(FILE_NAME);
                    String directoryName;

                    switch (fieldName) {
                        case IMAGE_FILE:
                            directoryName = properties.getProperty(IMAGES_DIRECTORY);
                            break;
                        case AUDIO_FILE:
                            directoryName = properties.getProperty(MUSICS_DIRECTORY);
                            break;
                        default:
                            throw new DataException("Unknown type field name! \"" + fileName + "\"");
                    }

                    item.write(new File(directoryName + File.separator + fileName));
                }
            }

        } catch (Exception e) {
            throw new DataException(e.getMessage(), e);

        } finally {
            lock.unlock();
            semaphore.release();
        }

    }

}
