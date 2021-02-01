package com.epam.task.third.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TxtFileReaderTest {
    private TxtFileReader reader = new TxtFileReader();
    private final String FILE_NAME = "src/test/resources/input.txt";
    private List<String> expected = Arrays.asList("5.0 3.0 0.0 5.0",
                                                  "2.n -4 4",
                                                  "6 2.5 0 ?",
                                                  "-2.0 -1.0 1.0 -5.0");
    private final String WRONG_FILE_NAME = "src/test/resources/input";
    private boolean expectedException = true;

    @Test
    public void testReadShouldReadDataWhenFileNameApplied() throws DataException{
        //when
        List<String> actual = reader.read(FILE_NAME);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReadShouldThrowExceptionWhenWrongFileNameApplied(){
        //given
        boolean actualException;

        //when
        try {
            List<String> actual = reader.read(WRONG_FILE_NAME);
             actualException = false;
            Assert.assertEquals(expectedException, actualException);
        }

        //then
        catch (DataException e) {
            actualException = true;
            Assert.assertEquals(expectedException, actualException);
        }
    }

}
