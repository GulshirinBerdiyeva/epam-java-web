package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.apache.log4j.Logger;

public class StringTextHandler implements ITextHandler{
    private final static Logger LOGGER = Logger.getLogger(StringTextHandler.class);
    private final String vowels = "aeiou";

    private String[] splitIntoWords(Text text, String regex){
        String copyText = text.toString();
        String[] words = copyText.split(regex);
        return words;
    }

    public String replaceLetter(Text text, int index, char newChar){
        String[] words = splitIntoWords(text, "\\W");
        StringBuilder buffer = new StringBuilder();

        for (String word : words){
            if (word.length() > index){
                buffer.append(word, 0, index);
                buffer.append(newChar + word.substring(index + 1, word.length()) + " ");
            }else {
                LOGGER.info(word + " length less than index");
            }
        }
        String result = buffer.toString();
        return result.trim();
    }

    public String changeAll_PA_to_PO(Text text) {
        String[] words = splitIntoWords(text, "\\W");
        StringBuilder buffer = new StringBuilder();

        for (String word : words){
            boolean includePA = word.contains("pa");
            if (includePA){
                int index = word.indexOf("pa");
                StringBuilder temp = new StringBuilder(word);
                temp.setCharAt(index+1, 'o');
                buffer.append(temp + " ");
            }else {
                LOGGER.info(word + " without 'pa'");
            }
        }
        String result = buffer.toString();
        return result.trim();
    }

    public String replaceAllWords(Text text, int length, String replacement) {
        String[] words = splitIntoWords(text, "\\W");
        StringBuilder buffer = new StringBuilder(text.toString());

        for (String word : words){
            boolean equal = word.length()==length;
            if(equal){
                int index = buffer.indexOf(word);
                buffer.delete(index, index+length);
                buffer.insert(index, replacement);
            }
        }
        String result = buffer.toString();
        return result.trim();
    }

    public String splitTextIntoWordsAndSpaces(Text text) {
        String[] words = splitIntoWords(text, "\\W");
        StringBuilder buffer = new StringBuilder();

        for (String word : words){
            if (!word.isEmpty()){
                buffer.append(word+" ");
            }
        }
        String result = buffer.toString();
        return result.trim();
    }

    public String removeAllWordsStartingWithConsonant(Text text, int length) {
        String[] words = splitIntoWords(text, "[^a-zA-z]");
        StringBuilder buffer = new StringBuilder();

        for (String word : words){
            int wordLength = word.length();
            boolean isEqual = wordLength == length;
            if (!word.isEmpty() && isEqual){
                Character firstLetter = word.charAt(0);
                boolean isVowel = vowels.contains(firstLetter.toString());
                if (isVowel){
                    LOGGER.info(word + " starts with vowel");
                    buffer.append(word + " ");
                }else {
                    LOGGER.info(word + " deleted");
                }
            }else if(!word.isEmpty()){
                buffer.append(word + " ");
            }
        }
        String result = buffer.toString();
        return result.trim();
    }
}
