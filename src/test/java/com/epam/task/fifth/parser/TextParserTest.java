package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import com.epam.task.fifth.entity.LexemeType;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TextParserTest {
    private final String TEXT = "\tKeep it simple. " +
                                "\tKeep it real!";
    private final Component PARAGRAPH_1 = new Composite(Arrays.asList(
                                                                new Lexeme("Keep", LexemeType.WORD),
                                                                new Lexeme("it", LexemeType.WORD),
                                                                new Lexeme("simple", LexemeType.WORD)));
    private final Component PARAGRAPH_2 = new Composite(Arrays.asList(
                                                                new Lexeme("Keep", LexemeType.WORD),
                                                                new Lexeme("it", LexemeType.WORD),
                                                                new Lexeme("real", LexemeType.WORD)));
    private final Composite EXPECTED = new Composite();

    @Test
    public void testParseShouldReturnParagraphsWhenTextApplied(){
        //given
        EXPECTED.add(PARAGRAPH_1);
        EXPECTED.add(PARAGRAPH_2);

        ParagraphsParser nextParser = Mockito.mock(ParagraphsParser.class);
        when(nextParser.getSuccessor()).thenReturn(nextParser);
        when(nextParser.parse(anyString())).thenReturn(PARAGRAPH_1, PARAGRAPH_2);

        TextParser parser = new TextParser(nextParser);

        //when
        Component actual = parser.parse(TEXT);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}