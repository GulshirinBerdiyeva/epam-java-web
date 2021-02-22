package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class TextParserTest {
    private final String TEXT = "\tIt has survived - not only [5 20 5 + /] centuries, " +
                                "but also the leap into [6 7 *] electronic typesetting, " +
                                "remaining essentially [2 3 + 10 *] unchanged. " +
                                "It was popularised in the [+ 2 5 - 3] with the release of " +
                                "Letraset sheets containing Lorem Ipsum passages, and more " +
                                "recently with desktop publishing software like Aldus PageMaker " +
                                "including versions of Lorem Ipsum.    " +
                                "It is a long established fact that a reader will be distracted " +
                                "by the readable content of a page when looking at its layout. " +
                                "The point of using [42 6 / 3 +] Ipsum is that it has a more-or-less " +
                                "normal distribution of letters, as opposed to using (Content here), " +
                                "content here, making it look like readable English.    " +
                                "It is a [4 3 * 6 / 8 +] established fact that a reader will be of a page " +
                                "when looking at its layout.    Bye.";
    private final Component PARAGRAPH_1 = new Composite("It has survived - not only [5 20 5 + /] centuries, " +
                                                                    "but also the leap into [6 7 *] electronic typesetting, " +
                                                                    "remaining essentially [2 3 + 10 *] unchanged. " +
                                                                    "It was popularised in the [+ 2 5 - 3] with the release of " +
                                                                    "Letraset sheets containing Lorem Ipsum passages, " +
                                                                    "and more recently with desktop publishing software like " +
                                                                    "Aldus PageMaker including versions of Lorem Ipsum.");
    private final Component PARAGRAPH_2 = new Composite("It is a long established fact that a reader will be distracted " +
                                                                "by the readable content of a page when looking at its layout. " +
                                                                "The point of using [42 6 / 3 +] Ipsum is that it has a " +
                                                                "more-or-less normal distribution of letters, as opposed to using " +
                                                                "(Content here), content here, making it look like readable English.");
    private final Component PARAGRAPH_3 = new Composite("It is a [4 3 * 6 / 8 +] established fact that a reader " +
                                                                    "will be of a page when looking at its layout.");
    private final Component PARAGRAPH_4 = new Composite("Bye.");
    private final Composite EXPECTED = new Composite();

    @Test
    public void testParseShouldReturnParagraphsCompositeWhenTextApplied(){
        //given
        EXPECTED.add(PARAGRAPH_1);
        EXPECTED.add(PARAGRAPH_2);
        EXPECTED.add(PARAGRAPH_3);
        EXPECTED.add(PARAGRAPH_4);
        ParagraphsParser nextParser = Mockito.mock(ParagraphsParser.class);
        when(nextParser.getSuccessor()).thenReturn(nextParser);
        when(nextParser.parse(anyString())).thenReturn(PARAGRAPH_1, PARAGRAPH_2, PARAGRAPH_3, PARAGRAPH_4);
        TextParser parser = new TextParser(nextParser);

        //when
        Component actual = parser.parse(TEXT);

        //then
        Assert.assertEquals(EXPECTED, actual);
    }

}