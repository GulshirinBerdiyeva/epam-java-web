package com.epam.task.fifth.build;

import com.epam.task.fifth.parser.ParagraphsParser;
import com.epam.task.fifth.parser.Parser;
import com.epam.task.fifth.parser.SentencesParser;
import com.epam.task.fifth.parser.TextParser;

public class ChainBuilder {

    public Parser build(){
        return new TextParser(new ParagraphsParser(new SentencesParser()));
    }

}