package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import com.epam.task.fifth.entity.LexemeType;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentencesParserTest {
    private final String SENTENCE = "Solve it - [5 20 5 + /] please...";
    private final Component EXPECTED = new Composite(Arrays.asList(
                                                            new Lexeme("Solve", LexemeType.WORD),
                                                            new Lexeme("it", LexemeType.WORD),
                                                            new Lexeme("[5 20 5 + /]", LexemeType.EXPRESSION),
                                                            new Lexeme("please", LexemeType.WORD)));

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