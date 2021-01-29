package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTextHandler implements ITextHandler{

    private final static Logger LOGGER = Logger.getLogger(RegExTextHandler.class);

    public String splitTextIntoWordsAndSpaces(Text text) {
        String copyText = text.toString();
        Pattern pattern = Pattern.compile("[^\\p{Punct}&&\\D]");
        Matcher matcher = pattern.matcher(copyText);
        StringBuilder buffer = new StringBuilder();

        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            buffer.append(copyText.substring(start, end));
        }
        LOGGER.info("Text splitted successfully by regex");
        String result = buffer.toString();
        return result.trim();
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
