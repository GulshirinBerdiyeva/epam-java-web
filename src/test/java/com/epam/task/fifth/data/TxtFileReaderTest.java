package com.epam.task.fifth.data;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TxtFileReaderTest {
    private TxtFileReader reader = new TxtFileReader();
    private final String VALID_FILE_NAME = "src/test/resources/input.txt";
    private final String INVALID_FILE_NAME = "src/test/resources/input";

    private final String EXPECTED = "    It has survived - not only [5 20 5 + /] centuries, " +
                                    "but also the leap into [6 7 *] electronic typesetting, " +
                                    "remaining essentially [2 3 + 10 *] unchanged. " +
                                    "It was popularised in the [+ 2 5 - 3] with the release of " +
                                    "Letraset sheets containing Lorem Ipsum passages, and more " +
                                    "recently with desktop publishing software like Aldus PageMaker " +
                                    "including versions of Lorem Ipsum.    " +
                                    "It is a long established fact that a reader will be distracted " +
                                    "by the readable content of a page when looking at its layout. " +
                                    "The point of using [42 6 / 3 +] Ipsum is that it has a more-or-less " +
                                    "normal distribution of letters, as opposed to using (Content here), " +
                                    "content here, making it look like readable English.    " +
                                    "It is a [4 3 * 6 / 8 +] established fact that a reader will be of a page " +
                                    "when looking at its layout.    Bye.";

    @Test
    public void testReadShouldReturnTextWhenValidFileApplied() throws DataException, IOException {
        //when
        String actual = reader.read(VALID_FILE_NAME);

        //then
        Assert.assertEquals(actual, EXPECTED);
    }

    @Test
    public void testReadShouldThrowDataExceptionWhenInvalidFileNameApplied() {
        Assert.assertThrows(DataException.class, () -> reader.read(INVALID_FILE_NAME));
    }

}