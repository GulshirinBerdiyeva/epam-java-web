package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentencesParserTest {
    private final static String SENTENCE = "Solve it - [5 20 5 + /] please...";
    private final static Component EXPECTED = new Composite(Arrays.asList(
                                                            Lexeme.word("Solve"),
                                                            Lexeme.word("it"),
                                                            Lexeme.expression("[5 20 5 + /]"),
                                                            Lexeme.word("please")));

    @Test
    public void testParseShouldReturnLexemesWhenSentenceApplied(){
        //given
        SentencesParser parser = new SentencesParser();

        //when
        Component actual = parser.parse(SENTENCE);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}