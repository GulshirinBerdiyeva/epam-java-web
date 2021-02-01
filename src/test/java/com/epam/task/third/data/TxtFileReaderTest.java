package com.epam.task.third.data;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TxtFileReaderTest {
    private TxtFileReader reader = new TxtFileReader();
    private final String FILE_NAME = "src/test/resources/input.txt";
    private final List<String> EXPECTED = Arrays.asList("5.0 3.0 0.0 5.0",
                                                        "2.n -4 4",
                                                        "6 2.5 0 ?",
                                                        "-2.0 -1.0 1.0 -5.0");
    private final String WRONG_FILE_NAME = "src/test/resources/input";
    private final boolean EXPECTED_EXCEPTION = true;

    @Test
    public void testReadShouldReadDataWhenFileNameApplied() throws DataException, IOException {
        //when
        List<String> actual = reader.read(FILE_NAME);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test
    public void testReadShouldThrowExceptionWhenWrongFileNameApplied() throws IOException {
        //given
        boolean actualException = false;

        //when
        try {
            List<String> data = reader.read(WRONG_FILE_NAME);
            Assert.assertEquals(EXPECTED_EXCEPTION, actualException);
        }

        //then
        catch (DataException e) {
            actualException = true;
            Assert.assertEquals(EXPECTED_EXCEPTION, actualException);
        }
    }

}
