package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;

public interface Parser {
    Component parse(String input);
}