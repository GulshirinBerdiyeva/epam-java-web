package com.epam.task.third.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DataReaderTest {
    private DataReader reader = new DataReader();
    private final String FILE_NAME = "src/test/resources/input.txt";
    private final String WRONG_FILE_NAME = "src/test/resources/input";
    private final List<String> EXPECTED = Arrays.asList("5.0 3.0 0.0 5.0",
                                                        "2.n -4 4",
                                                        "-6.0 2.5 3.0 4.0",
                                                        "6 2.5 0 ?",
                                                        "-2.0 -1.0 1.0 -5.0",
                                                        "7.0 3.5 -2.0 5.5");

    @Test
    public void testReadShouldReadDataWhenFileNameApplied() throws DataException {
        //when
        List<String> actual = reader.read(FILE_NAME);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test(expected = DataException.class)
    public void testReadShouldThrowExceptionWhenWrongFileNameApplied() throws DataException {
        //when
        List<String> actual = reader.read(WRONG_FILE_NAME);
    }

}