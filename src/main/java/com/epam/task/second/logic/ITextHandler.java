package com.epam.task.second.logic;

import com.epam.task.second.entity.Text;

public interface ITextHandler {
    Text replaceLetter(Text text, int index, char newChar);
    Text changeAll_PA_to_PO(Text text);
    Text replaceAllWords(Text text, int length, Text replacement);
    Text splitTextOnlyIntoLettersAndSpaces(Text text);
    Text removeAllWordsStartingWithConsonant(Text text, int length);
}
