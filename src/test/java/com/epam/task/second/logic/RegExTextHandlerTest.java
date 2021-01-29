package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.junit.Assert;
import org.junit.Test;

public class RegExTextHandlerTest {
    private RegExTextHandler textHandler = new RegExTextHandler();

    @Test
    public void testSplitTextIntoLettersAndSpacesShouldSplitTextIntoWordsAndSpacesWhenTextApplied(){
        //given
        Text text = new Text("8Music in the @soul can be? heard by+ the #universe0");
        String expected = "Music in the soul can be heard by the universe";

        //when
        String actual = textHandler.splitTextIntoWordsAndSpaces(text);

        //then
        Assert.assertEquals(expected, actual);
    }

}
