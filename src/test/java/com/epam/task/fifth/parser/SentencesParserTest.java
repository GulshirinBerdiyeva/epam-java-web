package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import com.epam.task.fifth.entity.LexemeType;
import org.junit.Assert;
import org.junit.Test;

public class SentencesParserTest {
    private final String SENTENCE = "It has survived - not only [5 20 5 + /] centuries.";
    private final Component WORD_1 = new Lexeme("It", LexemeType.WORD);
    private final Component WORD_2 = new Lexeme("has", LexemeType.WORD);
    private final Component WORD_3 = new Lexeme("survived", LexemeType.WORD);
    private final Component WORD_4 = new Lexeme("not", LexemeType.WORD);
    private final Component WORD_5 = new Lexeme("only", LexemeType.WORD);
    private final Component WORD_6 = new Lexeme("[5 20 5 + /]", LexemeType.EXPRESSION);
    private final Component WORD_7 = new Lexeme("centuries", LexemeType.WORD);
    private final Composite EXPECTED = new Composite();

    @Test
    public void testParseShouldReturnLexemesWhenSentenceApplied(){
        //given
        EXPECTED.add(WORD_1);
        EXPECTED.add(WORD_2);
        EXPECTED.add(WORD_3);
        EXPECTED.add(WORD_4);
        EXPECTED.add(WORD_5);
        EXPECTED.add(WORD_6);
        EXPECTED.add(WORD_7);
        SentencesParser parser = new SentencesParser(null);

        //when
        Component actual = parser.parse(SENTENCE);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}