package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.apache.log4j.Logger;

public class StringTextHandler implements ITextHandler{

    private final static Logger LOGGER = Logger.getLogger(StringTextHandler.class);

    private String[] split(Text text, String regex){
        String copyText = text.toString();
        String[] words = copyText.split(regex);
        return words;
    }

    public String replaceLetter(Text text, int index, char newChar){
        String[] words = split(text, "\\W");
        StringBuilder result = new StringBuilder();

        for (String word : words){
            if (word.length() > index){
                result.append(word, 0, index);
                result.append(newChar + word.substring(index + 1, word.length()) + " ");
            }else {
                LOGGER.info(word + " length less than index");
            }
        }
        return String.valueOf(result);
    }

    public String changeAll_PA_to_PO(Text text) {
        String[] words = split(text, "\\W");
        StringBuilder result = new StringBuilder();

        for (String word : words){
            boolean includePA = word.contains("pa");
            if (includePA){
                int index = word.indexOf("pa");
                StringBuilder temp = new StringBuilder(word);
                temp.setCharAt(index+1, 'o');
                result.append(temp + " ");
            }else {
                LOGGER.info(word + " without 'pa'");
            }
        }
        return String.valueOf(result);
    }

    public String replaceAllWords(Text text, int length, String replacement) {
        String[] words = split(text, "\\W");
        StringBuilder result = new StringBuilder(text.toString());

        for (String word : words){
            boolean equal = word.length()==length;
            if(equal){
                int index = result.indexOf(word);
                result.delete(index, index+length);
                result.insert(index, replacement);
            }
        }
        return result.toString();
    }

    public String splitTextOnlyIntoWordsAndSpaces(Text text) {
        String[] words = split(text, "[^a-zA-z]");
        StringBuilder result = new StringBuilder();

        for (String word : words){
            if (word.length() > 0){
                result.append(word+" ");
            }
        }
        return result.toString();
    }

    public String removeAllWordsStartingWithConsonant(Text text, int length) {
        String[] words = split(text, "[^a-zA-z]");
        StringBuilder result = new StringBuilder();
        String vowels = "aeiou";

        for (String word : words){
            int size = word.length();
            if (size > 0){
                if (size != length){
                    result.append(word + " ");
                }else {
                    Character firstLetter = word.charAt(0);
                    boolean isVowel = vowels.contains(firstLetter.toString());
                    if (isVowel){
                        LOGGER.info(word + " starts with vowel");
                        result.append(word + " ");
                    }else {
                        LOGGER.info(word + " deleted");
                    }
                }
            }
        }
        return result.toString();
    }
}
