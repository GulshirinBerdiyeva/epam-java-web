package com.epam.task.web.project.validator;

import org.junit.Assert;
import org.junit.Test;

public class FileNameValidatorTest {

    private static final String VALID_MP3_FILE = "music.mp3";
    private static final String VALID_JPG_FILE = "photo.jpg";
    private static final String INVALID_MP3_FILE = ".mp33";
    private static final String INVALID_JPG_FILE = ".jpg";

    private final FileNameValidator fileNameValidator = new FileNameValidator();

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidMp3File() {
        boolean actual = fileNameValidator.isValid(VALID_MP3_FILE);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnTrueWhenAppliedValidJpgFile() {
        boolean actual = fileNameValidator.isValid(VALID_JPG_FILE);

        Assert.assertTrue(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedValidMp3File() {
        boolean actual = fileNameValidator.isValid(INVALID_MP3_FILE);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenAppliedValidJpgFile() {
        boolean actual = fileNameValidator.isValid(INVALID_JPG_FILE);

        Assert.assertFalse(actual);
    }

    @Test
    public void isValidShouldReturnFalseWhenValueNull() {
        boolean actual = fileNameValidator.isValid(null);

        Assert.assertFalse(actual);
    }

    @Test
    public void isNumberValidShouldReturnFalseWhenAppliedEmptyValue() {
        boolean actual = fileNameValidator.isValid("     ");

        Assert.assertFalse(actual);
    }

}