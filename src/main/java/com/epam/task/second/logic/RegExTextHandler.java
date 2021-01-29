package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTextHandler implements ITextHandler{
    public String replaceLetter(Text text, int index, char newChar) {
        String copyText = text.toString();
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(copyText);
        int begin = 0,
            end = 0;
        do {
            begin=5;
        }while (matcher.find());


        return null;
    }

    public String changeAll_PA_to_PO(Text text) {
        return null;
    }

    public String replaceAllWords(Text text, int length, String replacement) {
        return null;
    }

    public String splitTextOnlyIntoLettersAndSpaces(Text text) {
        return null;
    }

    public String removeAllWordsStartingWithConsonant(Text text, int length) {
        return null;
    }
}
