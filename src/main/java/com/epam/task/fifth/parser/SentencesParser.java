package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import com.epam.task.fifth.entity.LexemeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentencesParser extends AbstractParser{
    private final static Logger LOGGER = LogManager.getLogger(SentencesParser.class);
    private final Pattern PATTERN = Pattern.compile("(\\w+)|(\\[.+\\])");

    public SentencesParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String input) {
        Matcher matcher = PATTERN.matcher(input);
        List<String> list = new ArrayList<>();

        while (matcher.find()){
            list.add(matcher.group());
        }

        Composite text = new Composite();
        list.stream()
                .forEach(lexeme -> {
                    if (lexeme.startsWith("[")) {
                        text.add(new Lexeme(lexeme, LexemeType.EXPRESSION));
                    } else {
                        text.add(new Lexeme(lexeme, LexemeType.WORD));
                    }
                });

        LOGGER.info("Parsed by SentencesParser");
        return text;
    }

}