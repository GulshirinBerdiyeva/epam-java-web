package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.junit.Assert;
import org.junit.Test;

public class CharTextHandlerTest {
    private CharTextHandler textHandler = new CharTextHandler();

    @Test
    public void testSplitTextOnlyIntoLettersAndSpacesShouldSplitTextOnlyIntoWordsAndSpacesWhenTextApplied(){
        //given
        Text text = new Text("8Music in the @soul can be? heard by+ the #universe0");
        String expected = "Music in the soul can be heard by the universe ";

        //when
        String actual = textHandler.splitTextOnlyIntoWordsAndSpaces(text);

        //then
        Assert.assertEquals(expected, actual);
    }

}
