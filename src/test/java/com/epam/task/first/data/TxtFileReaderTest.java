package com.epam.task.first.data;

import org.junit.Test;

import java.util.List;

public class TxtFileReaderTest {

    private TxtFileReader reader = new TxtFileReader();

    @Test
    public void testReadShouldReadDataFromAppliedFile() throws FileException {
        //given
        final String filename = "src/test/resources/input.txt";

        //when
        List<String> data = reader.read(filename);

        //then
        System.out.println(data);
    }

}
