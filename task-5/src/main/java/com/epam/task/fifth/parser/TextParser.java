package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class TextParser extends AbstractParser{
    private final static Logger LOGGER = LogManager.getLogger(TextParser.class);

    private final static Pattern PATTERN = Pattern.compile("[^\\t]+");

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String input) {
        setPattern(PATTERN);

        LOGGER.info("Parsed by TextParser");
        return super.parse(input);
    }

}