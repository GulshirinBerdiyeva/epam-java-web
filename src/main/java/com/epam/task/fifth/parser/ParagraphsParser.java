package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class ParagraphsParser extends AbstractParser {
    private final static Logger LOGGER = LogManager.getLogger(ParagraphsParser.class);
    private final Pattern PATTERN = Pattern.compile("\\p{Upper}.[^\\.!?]+");

    public ParagraphsParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String input) {
        setPattern(PATTERN);

        LOGGER.info("Parsed by ParagraphsParser");
        return super.parse(input);
    }

}