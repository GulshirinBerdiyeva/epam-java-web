package com.epam.task.first.data;

import org.junit.Test;

import java.util.List;

public class TxtFileReaderTest {

    private TxtFileReader reader = new TxtFileReader();

    @Test
    public void testReadShouldReadDataWhenAppliedFilePath(){
        //given
        final String filename = "src/test/resources/input.txt";

        //when
        List<String> data = null;
        try {
            data = reader.read(filename);

            //then
            System.out.println(data);
        } catch (FileException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadShouldThrowFileExceptionWhenAppliedInvalidFilePath(){
        //given
        final String filename = "src/test/resources/input";

        //when
        List<String> data = null;
        try {
            data = reader.read(filename);

            //then
            System.out.println(data);
        } catch (FileException e) {
            e.printStackTrace();
        }
    }

}
