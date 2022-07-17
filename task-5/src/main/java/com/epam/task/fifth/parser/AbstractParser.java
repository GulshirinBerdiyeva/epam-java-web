package com.epam.task.fifth.parser;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractParser implements Parser{
    private Parser successor;
    private Pattern pattern;

    public AbstractParser(Parser successor) {
        this.successor = successor;
    }

    protected Parser getSuccessor() {
        return successor;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Component parse(String input) {
        Matcher matcher = pattern.matcher(input);
        List<String> children = new ArrayList<>();

        while (matcher.find()){
            children.add(matcher.group());
        }

        Composite result = new Composite();

        children.stream()
                .forEach( child -> {
                    Component component = getSuccessor().parse(child);
                    result.add(component);
                });

        return result;
    }

}