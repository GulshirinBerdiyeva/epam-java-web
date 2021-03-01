package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
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
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("simple"))),
                                                        new Composite(Arrays.asList(
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("real")))));
    private final Component PARAGRAPH_2 = new Composite(Arrays.asList(
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("simple")));
    private final Component LEXEME_1 = Lexeme.word("Solve");
    private final Component LEXEME_2 = Lexeme.word("it");
    private final Component LEXEME_3 = Lexeme.expression("[5 20 5 + /]");
    private final String EXPECTED_TEXT = "Solve it [5 20 5 + /]";
    private final Component EXPRESSION = Lexeme.expression("6 7 49 / * ");
    private final Double RESULT = 42.0;
    private final Component EXPECTED_EXPRESSION = Lexeme.expression(RESULT.toString());

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