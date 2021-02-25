package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import com.epam.task.fifth.entity.LexemeType;
import com.epam.task.fifth.interpreter.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TextLogicTest {
    private final TextLogic textLogic = new TextLogic();
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
    private final String EXPECTED_TEXT = "Solve it [5 20 5 + /]";
    private final Component EXPRESSION = new Lexeme("6 7 49 / * ", LexemeType.EXPRESSION);
    private final Double RESULT = 42.0;
    private final Component EXPECTED_EXPRESSION = new Lexeme(RESULT.toString(), LexemeType.EXPRESSION);

    @Test
    public void testSortParagraphsShouldReturnAscendingSortedParagraphsWhenTextApplied(){
        //given
        Composite TEXT = new Composite();
        Composite EXPECTED = new Composite();

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
        Composite SENTENCE = new Composite();
        Composite EXPECTED = new Composite();

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
        Composite SENTENCE = new Composite();

        SENTENCE.add(LEXEME_1);
        SENTENCE.add(LEXEME_2);
        SENTENCE.add(LEXEME_3);

        //when
        String actual = textLogic.restore(SENTENCE);

        //then
        Assert.assertEquals(EXPECTED_TEXT, actual);
    }

    @Test
    public void testCalculateShouldReturnExpressionLexemeWhenExpressionStringApplied(){
        //given
        ExpressionCalculator calculator = Mockito.mock(ExpressionCalculator.class);
        when(calculator.calculate(anyString())).thenReturn(RESULT);

        //when
        Component actual = textLogic.calculate(EXPRESSION, calculator);

        //then
        Assert.assertEquals(EXPECTED_EXPRESSION, actual);
    }

}