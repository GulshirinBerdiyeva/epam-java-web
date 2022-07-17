package com.epam.task.web.project.validator;

import java.util.regex.Pattern;

public class FileNameValidator extends AbstractValidator {

    private static final String MP3 = "mp3";
    private static final String JPG = "jpg";
    private static final String AUDIO_FILE = "audioFile";
    private static final String IMAGE_FILE = "imageFile";
    private static final String MP3_FILE_NAME_REGEX = "[A-Za-zА-Яа-яЁё]+\\.(mp3)";
    private static final String JPG_FILE_NAME_REGEX = "[A-Za-zА-Яа-яЁё]+\\.(jpg)";

    @Override
    public boolean isValid(String inputValue) {
        if (!isNullOrEmpty(inputValue)) {
            if (inputValue.contains(MP3)) {
                return Pattern.matches(MP3_FILE_NAME_REGEX, inputValue);
            }

            if (inputValue.contains(JPG)) {
                return Pattern.matches(JPG_FILE_NAME_REGEX, inputValue);
            }
        }

        return false;
    }


    public boolean isValid(String inputValue, String name) {
        if (!isNullOrEmpty(inputValue) && !isNullOrEmpty(name)) {

            if (AUDIO_FILE.equals(name) && inputValue.contains(MP3)) {
                return Pattern.matches(MP3_FILE_NAME_REGEX, inputValue);

            } else if (IMAGE_FILE.equals(name) && inputValue.contains(JPG)) {
                return Pattern.matches(JPG_FILE_NAME_REGEX, inputValue);
            }
        }

        return false;
    }

}
