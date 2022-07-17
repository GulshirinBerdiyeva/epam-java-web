package com.epam.task.third.tool;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineValidator {
    private final static Logger LOGGER = Logger.getLogger(LineValidator.class);

    private final String doubleNumber = " *-?\\d+\\.\\d+ *";
    private final int numbersAmount = 4;

    public Boolean isValid(String line){
        Pattern pattern = Pattern.compile(doubleNumber);
        Matcher matcher = pattern.matcher(line);
        StringBuilder buffer = new StringBuilder();
        int amount = 0;

        while (matcher.find()){
            buffer.append(matcher.group());
            amount++;
        }

        boolean isCorrectAmount = amount == numbersAmount;
        boolean isEqual = line.contentEquals(buffer);
        boolean isValid = isCorrectAmount && isEqual;

        LOGGER.info("Line validation = " + isValid);
        return isValid;
    }

}