package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTextHandler implements ITextHandler{

    private final static Logger LOGGER = Logger.getLogger(RegExTextHandler.class);

    public String splitTextOnlyIntoWordsAndSpaces(Text text) {
        String copyText = text.toString();
        Pattern pattern = Pattern.compile("[^\\p{Punct}&&\\D]");
        Matcher matcher = pattern.matcher(copyText);
        StringBuilder result = new StringBuilder();

        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            result.append(copyText.substring(start, end));
        }
        return result.toString();
    }

    public String replaceLetter(Text text, int index, char newChar) {
        return null;
    }

    public String changeAll_PA_to_PO(Text text) {
        return null;
    }

    public String replaceAllWords(Text text, int length, String replacement) {
        return null;
    }

    public String removeAllWordsStartingWithConsonant(Text text, int length) {
        return null;
    }
}
