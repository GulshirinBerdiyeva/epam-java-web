package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class ParagraphsParserTest {
    private final String PARAGRAPH = "It is a long established fact that a reader " +
                                    "will be distracted by the readable content of a page " +
                                    "when looking at its layout. The point of using [42 6 / 3 +] " +
                                    "Ipsum is that it has a more-or-less normal distribution of letters, " +
                                    "as opposed to using (Content here), content here, making it look like readable English.";
    private final Component SENTENCES_1 = new Composite("It is a long established fact that a reader " +
                                                                "will be distracted by the readable content " +
                                                                "of a page when looking at its layout.");
    private final Component SENTENCES_2 = new Composite("The point of using [42 6 / 3 +] Ipsum is that it has a " +
                                                                    "more-or-less normal distribution of letters, " +
                                                                    "as opposed to using (Content here), " +
                                                                    "content here, making it look like readable English.");
    private final Composite EXPECTED = new Composite();

    @Test
    public void testParseShouldReturnSentencesWhenParagraphApplied(){
        //given
        EXPECTED.add(SENTENCES_1);
        EXPECTED.add(SENTENCES_2);
        SentencesParser nextParser = Mockito.mock(SentencesParser.class);
        when(nextParser.getSuccessor()).thenReturn(nextParser);
        when(nextParser.parse(anyString())).thenReturn(SENTENCES_1, SENTENCES_2);
        ParagraphsParser parser = new ParagraphsParser(nextParser);

        //when
        Component actual = parser.parse(PARAGRAPH);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}