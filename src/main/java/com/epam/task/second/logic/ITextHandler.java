package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;

public interface ITextHandler {
    String replaceLetter(Text text, int index, char newChar);
    String changeAll_PA_to_PO(Text text);
    String replaceAllWords(Text text, int length, String replacement);
    String splitTextOnlyIntoWordsAndSpaces(Text text);
    String removeAllWordsStartingWithConsonant(Text text, int length);
}
