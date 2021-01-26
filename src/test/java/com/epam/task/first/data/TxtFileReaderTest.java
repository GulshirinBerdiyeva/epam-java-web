package com.epam.task.first.data;

import org.junit.Test;

import java.util.List;

public class TxtFileReaderTest {

    private TxtFileReader reader = new TxtFileReader();

    @Test
    public void testReadShouldReadDataWhenValidFilePathApplied() throws FileException {
        //given
        final String FILE_NAME = "src/test/resources/input.txt";

        //when
        List<String> data = reader.read(FILE_NAME);

        //then
        System.out.println(data);
    }

    @Test
    public void testReadShouldThrowFileExceptionWhenInvalidFilePathApplied() throws FileException {
        //given
        final String FILE_NAME = "src/test/resources/input";

        //when
        try {
            List<String> data = reader.read(FILE_NAME);
        }

        //then
        catch (FileException e){
            new FileException("ERROR: File not found!", e.getCause());
        }
    }

}
