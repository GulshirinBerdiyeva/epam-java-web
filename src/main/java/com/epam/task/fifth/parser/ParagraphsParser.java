package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class ParagraphsParser extends AbstractParser {
    private final static Logger LOGGER = LogManager.getLogger(ParagraphsParser.class);
    private final String SPLITTER = "[.!?] *";

    public ParagraphsParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String input) {
        String[] sentences = input.split(SPLITTER);
        Composite text = new Composite();

        Stream.of(sentences)
                .filter( sentence -> !sentence.isEmpty() )
                .forEach( sentence -> {
                    Component component = getSuccessor().parse(sentence);
                    text.add(component);
                });

        LOGGER.info("Parsed by ParagraphsParser");
        return text;
    }

}