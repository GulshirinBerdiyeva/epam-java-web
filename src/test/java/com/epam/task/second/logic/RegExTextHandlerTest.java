package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.junit.Assert;
import org.junit.Test;

public class RegExTextHandlerTest {
    private RegExTextHandler textHandler = new RegExTextHandler();

    @Test
    public void testSplitTextIntoLettersAndSpacesShouldSplitTextIntoWordsAndSpacesWhenTextApplied(){
        //given
        Text text = new Text("8Music in the @soul can be? heard by+ the #universe 42");
        String expected = "8Music in the soul can be heard by the universe 42";

        //when
        String actual = textHandler.splitTextIntoWordsAndSpaces(text);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplaceAllWordsShouldReplaceAllWordsWhichLengthEqualsToGivenLengthWhenTextApplied() {
        //given
        Text text = new Text(" 42book orange< grapes home, car J8 lime map ");
        String expected = "42book orange grapes MAGIC car J8 MAGIC map";

        //when
        String actual = textHandler.replaceAllWords(text, 4, "MAGIC");

        //then
        Assert.assertEquals(expected, actual);
    }

}
