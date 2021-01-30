package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;
import org.apache.log4j.Logger;

public class CharTextHandler implements ITextHandler{
    private final static Logger LOGGER = Logger.getLogger(StringTextHandler.class);
    private final char[] regex = {' ', ',', '.', '?', '!'};
    private final char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    private boolean isValid(char symbol, char[] regex){
        boolean valid = true;
        for (int j = 0; j < regex.length; j++){
            if (symbol == regex[j]){
                valid = false;
                break;
            }
        }
        return valid;
    }

    private boolean isVowel(char symbol, char[] regex){
        boolean vowel = false;
        for (int j = 0; j < regex.length; j++){
            if (symbol == regex[j]){
                vowel = true;
                break;
            }
        }
        return vowel;
    }

    private StringBuilder splitIntoWords(Text text, char[] regex){
        String tempCopy = text.toString();
        char[] copyText = tempCopy.toCharArray();
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < copyText.length; i++){
            boolean valid = isValid(copyText[i], regex);
            if (valid){
                buffer.append(copyText[i]);
            }else {
                valid = isValid(copyText[i - 1], regex);
                if(valid){
                    buffer.append(" ");
                }
            }
        }
        return buffer;
    }

    public String splitTextIntoWordsAndSpaces(Text text) {
        StringBuilder buffer = splitIntoWords(text, regex);
        String result = buffer.toString();
        return result.trim();
    }

    public String removeAllWordsStartingWithConsonant(Text text, int length) {
        String temp = splitIntoWords(text, regex).toString();
        String[] words = temp.split(" ");
        StringBuilder buffer = new StringBuilder();

        for (String word : words){
            if (word.length() == length){
                boolean vowel = isVowel(word.charAt(0), vowels);
                if(!vowel){
                    LOGGER.info(word + " removed");
                }else {
                    buffer.append(word + " ");
                }
            }else {
                buffer.append(word + " ");
            }
        }
        String result = buffer.toString();
        return result.trim();
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

}