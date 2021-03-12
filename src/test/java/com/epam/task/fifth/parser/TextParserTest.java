package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TextParserTest {
    private final static String TEXT = "\tKeep it simple. " +
                                        "\tKeep it real!";
    private final static Component FIRST_PARAGRAPH = new Composite(Arrays.asList(
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("simple")));
    private final static Component SECOND_PARAGRAPH = new Composite(Arrays.asList(
                                                                Lexeme.word("Keep"),
                                                                Lexeme.word("it"),
                                                                Lexeme.word("real")));

    @Test
    public void testParseShouldReturnParagraphsWhenTextApplied(){
        //given
        Composite expected = new Composite();
        expected.add(FIRST_PARAGRAPH);
        expected.add(SECOND_PARAGRAPH);

        ParagraphsParser nextParser = Mockito.mock(ParagraphsParser.class);
        when(nextParser.getSuccessor()).thenReturn(nextParser);
        when(nextParser.parse(anyString())).thenReturn(FIRST_PARAGRAPH, SECOND_PARAGRAPH);

        TextParser parser = new TextParser(nextParser);

        //when
        Component actual = parser.parse(TEXT);

        //then
        Assert.assertEquals(expected, actual);
    }

}