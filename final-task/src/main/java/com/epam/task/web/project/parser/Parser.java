package com.epam.task.web.project.parser;

import java.util.List;
import java.util.Map;

/**
 * Generic interface
 * Implements by definite Parser classes
 *
 * @param <T> type of List values
 * */
public interface Parser<T> {
    /**
     * Map of parsed values
     *
     * @param values for parsing
     * @return Map Upper Bound Wildcard
     * @throws ParserException
     * */
    Map<? extends Object, ? extends Object> parse(List<T> values) throws ParserException;

}
