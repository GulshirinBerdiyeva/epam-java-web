package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import com.epam.task.fifth.entity.LexemeType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TextLogicTest {
    private final Component PARAGRAPH_1 = new Composite(Arrays.asList(
                                                        new Composite(Arrays.asList(
                                                                new Lexeme("Keep", LexemeType.WORD),
                                                                new Lexeme("it", LexemeType.WORD),
                                                                new Lexeme("simple", LexemeType.WORD))),
                                                        new Composite(Arrays.asList(
                                                                new Lexeme("Keep", LexemeType.WORD),
                                                                new Lexeme("it", LexemeType.WORD),
                                                                new Lexeme("real", LexemeType.WORD)))));
    private final Component PARAGRAPH_2 = new Composite(Arrays.asList(
                                                                new Lexeme("Keep", LexemeType.WORD),
                                                                new Lexeme("it", LexemeType.WORD),
                                                                new Lexeme("simple", LexemeType.WORD)));
    private final Component LEXEME_1 = new Lexeme("Solve", LexemeType.WORD);
    private final Component LEXEME_2 = new Lexeme("it", LexemeType.WORD);
    private final Component LEXEME_3 = new Lexeme("[5 20 5 + /]", LexemeType.EXPRESSION);

    private final Composite TEXT = new Composite();
    private final Composite SENTENCE = new Composite();
    private final Composite EXPECTED = new Composite();
    private final TextLogic textLogic = new TextLogic();
    private final String EXPECTED_TEXT = "Solve it [5 20 5 + /]";

    @Test
    public void testSortParagraphsShouldReturnAscendingSortedParagraphsWhenTextApplied(){
        //given
        TEXT.add(PARAGRAPH_1);
        TEXT.add(PARAGRAPH_2);

        EXPECTED.add(PARAGRAPH_2);
        EXPECTED.add(PARAGRAPH_1);

        //when
        Component actual = textLogic.sortParagraphs(TEXT);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test
    public void testSortSentencesShouldReturnAscendingSortedSentencesWhenSentenceApplied(){
        //given
        SENTENCE.add(LEXEME_1);
        SENTENCE.add(LEXEME_2);
        SENTENCE.add(LEXEME_3);

        EXPECTED.add(LEXEME_2);
        EXPECTED.add(LEXEME_1);
        EXPECTED.add(LEXEME_3);

        //when
        Component actual = textLogic.sortSentence(SENTENCE);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test
    public void testRestoreShouldReturnTextWhenComponentApplied(){
        //given
        SENTENCE.add(LEXEME_1);
        SENTENCE.add(LEXEME_2);
        SENTENCE.add(LEXEME_3);

        //when
        String actual = textLogic.restore(SENTENCE);

        //then
        Assert.assertEquals(EXPECTED_TEXT, actual);
    }

}