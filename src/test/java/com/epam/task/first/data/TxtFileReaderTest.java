package com.epam.task.first.data;

import org.junit.Test;

import java.util.List;

public class TxtFileReaderTest {
    private TxtFileReader reader = new TxtFileReader();
    private final String VALID_FILE = "src/test/resources/input.txt";
    private final String INVALID_FILE = "src/test/resources/input";



    @Test
    public void testReadShouldReadDataWhenValidFilePathApplied() throws FileException {
        //when
        List<String> data = reader.read(VALID_FILE);

        //then
        System.out.println(data);
    }

    @Test
    public void testReadShouldThrowFileExceptionWhenInvalidFilePathApplied() {
        //when
        try {
            List<String> data = reader.read(INVALID_FILE);
        }

        //then
        catch (FileException e){
            new FileException("ERROR: File not found!", e);
        }
    }

}
