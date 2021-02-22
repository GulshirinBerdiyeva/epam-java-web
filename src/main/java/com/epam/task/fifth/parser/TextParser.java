package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class TextParser extends AbstractParser{
    private final static Logger LOGGER = LogManager.getLogger(TextParser.class);
    private final String SPLITTER = "(\t)|( {4}?)";

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String input) {
        String[] paragraphs = input.split(SPLITTER);
        Composite text = new Composite();

        Stream.of(paragraphs)
                .filter( paragraph -> !paragraph.isEmpty() )
                .forEach( paragraph -> {
                    Component component = getSuccessor().parse(paragraph);
                    text.add(component);
                });

        LOGGER.info("Parsed by TextParser");
        return text;
    }

}