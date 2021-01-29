package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.apache.log4j.Logger;

public class CharTextHandler implements ITextHandler{

    private final static Logger LOGGER = Logger.getLogger(StringTextHandler.class);

    public String splitTextIntoWordsAndSpaces(Text text) {
        String copyText = text.toString();
        String regex = "[\\p{Punct}|\\d|\\s]";
        String[] words = copyText.split(regex);
        StringBuilder result = new StringBuilder();
        for (String word : words){
            if (word.length() > 0){
                result.append(word + " ");
                LOGGER.info(word + " splitted");
            }
        }
        return result.toString();
    }

    public String replaceAllWords(Text text, int length, String replacement) {
        return null;
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