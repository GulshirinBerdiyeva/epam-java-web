package com.epam.task.third.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String DOUBLE_NUMBER = " *-?\\d+\\.\\d+ *";

    public Boolean isValid(String line){
        Pattern pattern = Pattern.compile(DOUBLE_NUMBER);
        Matcher matcher = pattern.matcher(line);
        StringBuilder buffer = new StringBuilder();

        while (matcher.find()){
            buffer.append(matcher.group());
        }

        int correctLength = line.length();
        String checkedLine = buffer.toString();
        int length = checkedLine.length();

        return length == correctLength;
    }

}
