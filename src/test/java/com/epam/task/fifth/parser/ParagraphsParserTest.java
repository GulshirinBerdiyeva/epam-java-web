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

public class ParagraphsParserTest {
    private final static String PARAGRAPH = "Keep it simple. Keep it real!";
    private final static Component FIRST_SENTENCE = new Composite(Arrays.asList(
                                                            Lexeme.word("Keep"),
                                                            Lexeme.word("it"),
                                                            Lexeme.word("simple")));
    private final static Component SECOND_SENTENCE = new Composite(Arrays.asList(
                                                            Lexeme.word("Keep"),
                                                            Lexeme.word("it"),
                                                            Lexeme.word("real")));

    @Test
    public void testParseShouldReturnSentencesWhenParagraphApplied(){
        //given
        Composite expected = new Composite();
        expected.add(FIRST_SENTENCE);
        expected.add(SECOND_SENTENCE);

        SentencesParser nextParser = Mockito.mock(SentencesParser.class);
        when(nextParser.parse(anyString())).thenReturn(FIRST_SENTENCE, SECOND_SENTENCE);
        ParagraphsParser parser = new ParagraphsParser(nextParser);

        //when
        Component actual = parser.parse(PARAGRAPH);

        //then
        Assert.assertEquals(expected, actual);
    }

}