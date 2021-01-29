package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.junit.Assert;
import org.junit.Test;

public class RegExTextHandlerTest {
    RegExTextHandler textHandler = new RegExTextHandler();

    @Test
    public void testReplaceLetterShouldThrowExceptionWhenEmptyTextApplied(){
        //given
        Text text = new Text("inte$esting+* . Nort$ Kore$ on%ly$ plac$s");
        char symbol = '$';

        //when
        String actual = textHandler.replaceLetter(text, 4, symbol);

        //then
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testChangeAll_PA_to_POShouldChangeAll_PA_to_POWhenTextApplied(){
        //given
        //when
        //then
    }

    @Test
    public void testChangeReplaceAllWordsShouldReplaceAllWordsWhenTextApplied(){
        //given
        //when
        //then
    }

    @Test
    public void testSplitTextOnlyIntoLettersAndSpacesShouldSplitTextOnlyIntoLettersAndSpacesWhenTextApplied(){
        //given
        //when
        //then
    }

    @Test
    public void testRemoveAllWordsStartingWithConsonantShouldRemoveAllWordsStartingWithConsonantWhenTextApplied(){
        //given
        //when
        //then
    }

}
