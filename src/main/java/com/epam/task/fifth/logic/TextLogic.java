package com.epam.task.fifth.logic;

import com.epam.task.fifth.entity.Component;
import com.epam.task.fifth.entity.Composite;
import com.epam.task.fifth.entity.Lexeme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextLogic {

    protected List<Component> getChildren(Composite text){
        List<Component> children = new ArrayList<>();
        for (int i = 0; i < text.componentsCount(); i++){
            Component child = text.getChild(i);
            children.add(child);
        }
        return children;
    }

    public Component sortParagraphs(Composite text){
        List<Component> paragraphs = getChildren(text);

        Composite sortedParagraphs = new Composite(paragraphs.stream()
                .sorted((first, second) -> {
                    List<Component> firstChild = getChildren((Composite) first);
                    List<Component> secondChild = getChildren((Composite) second);
                    return secondChild.size() - firstChild.size();
                })
                .collect(Collectors.toList()));

        return sortedParagraphs;
    }

    public Component sortSentence(Composite sentence){
        List<Component> lexemes = getChildren(sentence);

        Composite sortedSentence = new Composite(lexemes.stream()
                .sorted((first, second) -> {
                    String firstLexeme = first.toString();
                    String secondLexeme = second.toString();
                    return firstLexeme.length() - secondLexeme.length();
                })
                .collect(Collectors.toList()));

        return sortedSentence;
    }

    public Component calculate(Component expression){

        return null;
    }

    public String restore(Composite sentence){
        List<Component> lexemes = getChildren(sentence);
        StringBuffer buffer = new StringBuffer();

        lexemes.stream()
                .forEach(lexeme -> {
                    buffer.append(lexeme.toString());
                    buffer.append(" ");
                });

        return buffer.toString().trim();
    }

}