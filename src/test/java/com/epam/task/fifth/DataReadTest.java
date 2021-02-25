package com.epam.task.fifth;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataReadTest {
    private final String VALID_FILE = "src/test/resources/input.txt";
    private final String EXPECTED = "    It has survived - not only [5 20 5 + /] centuries, " +
                                    "but also the leap into [6 7 *] electronic typesetting, " +
                                    "remaining essentially [2 3 + 10 *] unchanged. " +
                                    "It was popularised in the [+ 2 5 - 3] with the release of " +
                                    "Letraset sheets containing Lorem Ipsum passages, " +
                                    "and more recently with desktop publishing software " +
                                    "like Aldus PageMaker including versions of Lorem Ipsum." +
                                    "    It is a long established fact that a reader will be distracted " +
                                    "by the readable content of a page when looking at its layout. " +
                                    "The point of using [42 6 / 3 +] Ipsum is that it has a more-or-less " +
                                    "normal distribution of letters, as opposed to using (Content here), " +
                                    "content here, making it look like readable English." +
                                    "    It is a [4 3 * 6 / 8 +] established fact that a reader " +
                                    "will be of a page when looking at its layout.    Bye.";

    @Test
    public void testReadShouldReadTextFromFileWhenValidFileNameApplied() throws IOException {
        //given
        StringBuffer actual = new StringBuffer();

        //when
        Files.lines(Paths.get(VALID_FILE))
             .forEach(string -> actual.append(string));

        //then
        Assert.assertEquals(EXPECTED, actual.toString());
    }

}