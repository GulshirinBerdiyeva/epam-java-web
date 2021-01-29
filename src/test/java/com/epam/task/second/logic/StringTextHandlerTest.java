package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.junit.Assert;
import org.junit.Test;

public class StringTextHandlerTest {
    private StringTextHandler textHandler = new StringTextHandler();

    @Test
    public void testReplaceLetterShouldReplaceLetterInEachWordIfItsLengthInRangeOfIndexWhenTextApplied(){
        //given
        Text text = new Text("North Korea and Cuba are the only1 places you can't buy Coca-Cola.");
        String expected = "Nort$ Kore$ only$ plac$s ";
        char symbol = '$';

        //when
        String actual = textHandler.replaceLetter(text, 4, symbol);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testChangeAll_PA_to_POShouldChangeAll_PA_to_POWhenTextApplied(){
        //given
        Text text = new Text("apple respansibility impartant pop oppartunity");
        String expected = "responsibility important opportunity ";

        //when
        String actual = textHandler.changeAll_PA_to_PO(text);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReplaceAllWordsShouldReplaceAllWordsWhichLengthEqualsToGivenLengthWhenTextApplied(){
        //given
        Text text = new Text("Today, there are more than 700 different programming languages. " +
                            "Therefore, it is recommend for kids and programming beginners alike" +
                            " to start off with a visual editor and a blockly based programming language " +
                            "to learn it in a smoother and easier manner.");
        //wordsLength_amount
        //5_6
        //6_4
        //9_5

        // when
        String actual = textHandler.replaceAllWords(text, 9, "NINE");

        //then
        System.out.println(actual);
    }

    @Test
    public void testSplitTextIntoWordsAndSpacesShouldSplitTextIntoWordsAndSpacesWhenTextApplied(){
        //given
        Text text = new Text("8Music in the @soul can be? heard by+ the #universe0");
        String expected = "Music in the soul can be heard by the universe ";

        //when
        String actual = textHandler.splitTextIntoWordsAndSpaces(text);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRemoveAllWordsStartingWithConsonantShouldRemoveAllWordsStartingWithConsonantWhenTextApplied(){
        //given
        Text text = new Text("If you make ice cubes with tap water, they will be white; " +
                             "if you use boiled water, they will be transparent");
        String unexpected = "If make ice cubes with water they will be white" +
                            " if use boiled water they will be transparent ";

        //when
        String actual = textHandler.removeAllWordsStartingWithConsonant(text, 5);

        //then
        Assert.assertNotEquals(unexpected, actual);
    }

}
