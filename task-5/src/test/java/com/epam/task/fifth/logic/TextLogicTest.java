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
    private final static Component FIRST_PARAGRAPH = new Composite(Arrays.asList(
                                                        new Composite(Arrays.asList(
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("simple"))),
                                                        new Composite(Arrays.asList(
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("real")))));
    private final static Component SECOND_PARAGRAPH = new Composite(Arrays.asList(
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("simple")));

    private final static Component FIRST_LEXEME = Lexeme.word("Solve");
    private final static Component SECOND_LEXEME = Lexeme.word("it");
    private final static Component THIRD_LEXEME = Lexeme.expression("[5 20 5 + /]");

    private final static String EXPECTED_TEXT = "Solve it [5 20 5 + /]";
    private final static Component EXPRESSION = Lexeme.expression("6 7 49 / * ");
    private final static Double RESULT = 42.0;
    private final static Component EXPECTED_EXPRESSION = Lexeme.expression(RESULT.toString());

    @Test
    public void testSortParagraphsShouldReturnAscendingSortedParagraphsWhenTextApplied(){
        //given
        Composite text = new Composite();
        Composite expected = new Composite();

        text.add(FIRST_PARAGRAPH);
        text.add(SECOND_PARAGRAPH);

        expected.add(SECOND_PARAGRAPH);
        expected.add(FIRST_PARAGRAPH);

        //when
        Component actual = textLogic.sortParagraphs(text);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortSentencesShouldReturnAscendingSortedSentencesWhenSentenceApplied(){
        //given
        Composite sentence = new Composite();
        Composite expected = new Composite();

        sentence.add(FIRST_LEXEME);
        sentence.add(SECOND_LEXEME);
        sentence.add(THIRD_LEXEME);

        expected.add(SECOND_LEXEME);
        expected.add(FIRST_LEXEME);
        expected.add(THIRD_LEXEME);

        //when
        Component actual = textLogic.sortSentence(sentence);

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testRestoreShouldReturnTextWhenComponentApplied(){
        //given
        Composite sentence = new Composite();

        sentence.add(FIRST_LEXEME);
        sentence.add(SECOND_LEXEME);
        sentence.add(THIRD_LEXEME);

        //when
        String actual = textLogic.restore(sentence);

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