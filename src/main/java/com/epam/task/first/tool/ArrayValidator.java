package com.epam.task.first.tool;

public class ArrayValidator {

    public boolean isValid(String line){
        final String CORRECT_SYMBOLS = "0123456789 ";

        for (int i = 0; i < line.length(); i++){
            char symbol = line.charAt(i);
            boolean state = false;
            for (int j = 0; j < CORRECT_SYMBOLS.length(); j++){
                if (symbol == CORRECT_SYMBOLS.charAt(j)){
                    state = true;
                }
            }
            if (!state){
                return false;
            }
        }
        return true;
    }

}
