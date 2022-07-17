package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentencesParser implements Parser{
    private final static Logger LOGGER = LogManager.getLogger(SentencesParser.class);

    private final static Pattern PATTERN = Pattern.compile("(\\w+)|(\\[[\\d [\\+\\-\\*\\/]]+\\])");
    private final static String BRACKET = "[";

    @Override
    public Component parse(String input) {
        Matcher matcher = PATTERN.matcher(input);
        List<String> lexemes = new ArrayList<>();

        while (matcher.find()){
            lexemes.add(matcher.group());
        }

        Composite text = new Composite();

        lexemes.stream()
                .forEach(lexeme -> {
                    if (lexeme.startsWith(BRACKET)) {
                        text.add(Lexeme.expression(lexeme));
                    } else {
                        text.add(Lexeme.word(lexeme));
                    }
                });

        LOGGER.info("Parsed by SentencesParser");
        return text;
    }

}