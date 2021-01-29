package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTextHandler implements ITextHandler{

    private final static Logger LOGGER = Logger.getLogger(RegExTextHandler.class);

    private Matcher splitIntoWords(Text text) {
        String copyText = text.toString();
        Pattern pattern = Pattern.compile("\\w+[^\\p{Punct}]|\\s");
        Matcher matcher = pattern.matcher(copyText);
        return matcher;
    }

    public String splitTextIntoWordsAndSpaces(Text text) {
        Matcher matcher = splitIntoWords(text);
        StringBuilder buffer = new StringBuilder();

        while (matcher.find()){
            buffer.append(matcher.group());
        }
        LOGGER.info("Text splitted successfully by regex");
        String result = buffer.toString();
        return result.trim();
    }

    public String replaceAllWords(Text text, int length, String replacement) {
        String wordsTemp = splitTextIntoWordsAndSpaces(text);
        String[]  words = wordsTemp.split("\\s");
        StringBuilder buffer = new StringBuilder();

        for (String word : words){
            if (word.length() == length){
                buffer.append(replacement + " ");
                LOGGER.info(word + " replaced");
            }else {
                buffer.append(word + " ");
            }
        }
        String result = buffer.toString();
        return result.trim();
    }

    public String replaceLetter(Text text, int index, char newChar) {
        return null;
    }

    public String changeAll_PA_to_PO(Text text) {
        return null;
    }



    public String removeAllWordsStartingWithConsonant(Text text, int length) {
        return null;
    }
}
