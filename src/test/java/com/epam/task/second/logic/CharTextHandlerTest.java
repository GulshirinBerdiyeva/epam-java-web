package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.junit.Assert;
import org.junit.Test;

public class CharTextHandlerTest {
    private CharTextHandler textHandler = new CharTextHandler();

    @Test
    public void testSplitTextIntoWordsAndSpacesShouldSplitTextIntoWordsAndSpacesWhenTextApplied(){
        //given
        Text text = new Text("8Music in the !soul can be? heard by, the .universe0");
        String expected = "8Music in the soul can be heard by the universe0";

        //when
        String actual = textHandler.splitTextIntoWordsAndSpaces(text);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveAllWordsStartingWithConsonantShouldRemoveAllWordsStartingWithConsonantWhenTextApplied() {
        //given
        Text text = new Text("open black, !pink earn arpha? white idea blue, ");
        String expected = "open black earn arpha white idea";

        //when
        String actual = textHandler.removeAllWordsStartingWithConsonant(text, 4);

        //then
        Assert.assertEquals(expected, actual);
    }

}
