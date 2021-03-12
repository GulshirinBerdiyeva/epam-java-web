package com.epam.task.third.tool;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleParser {
    private final static Logger LOGGER = Logger.getLogger(DoubleParser.class);
    private final String DOUBLE_NUMBER = "-?\\d+\\.\\d+";

    public List<Double> parse(String line){
        List<Double> result = new ArrayList<Double>();
        Pattern pattern = Pattern.compile(DOUBLE_NUMBER);
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()){
            String temp = matcher.group();
            double number = Double.parseDouble(temp);
            result.add(number);
        }
        LOGGER.info("Line parsed");
        return result;
    }

}