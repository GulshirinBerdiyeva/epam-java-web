package com.epam.task.web.project.extractor;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.ServiceException;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class MusicExtractor {

    private static final String ENCODING = "UTF-8";
    private static final String TITLE = "title";
    private static final String ARTIST = "artist";
    private static final String PRICE = "price";
    private static final String IMAGE_FILE = "imageFile";
    private static final String IMAGES_DIRECTORY = "/images/";
    private static final String MUSICS_DIRECTORY = "/musics/";

    public Music extractData(List<FileItem> data) throws ServiceException {
        Music music = new Music();
        try {
            for(FileItem item : data) {
                if(item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String value = item.getString(ENCODING);

                    switch (fieldName) {
                        case TITLE:
                            music.setTitle(value);
                            break;
                        case ARTIST:
                            music.setArtist(value);
                            break;
                        case PRICE:
                            music.setPrice(new BigDecimal(value));
                            break;
                        default:
                            throw new ServiceException("Unknown field type!");
                    }

                } else {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();

                    if (IMAGE_FILE.equals(fieldName)) {
                        music.setImagePath(IMAGES_DIRECTORY + fileName);
                        item.write( new File(IMAGES_DIRECTORY + fileName));
                    } else {
                        music.setAudioPath(MUSICS_DIRECTORY + fileName);
                        item.write( new File(MUSICS_DIRECTORY + fileName));
                    }
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return music;
    }

}
