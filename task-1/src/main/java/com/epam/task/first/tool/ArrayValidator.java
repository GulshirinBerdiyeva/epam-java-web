package com.epam.task.first.tool;

public class ArrayValidator {
    private final String NUMBERS = "0123456789";

    public boolean isValid(String line){
        for (int i = 0; i < line.length(); i++){
            char symbol = line.charAt(i);
            boolean state = false;
            for (int j = 0; j < NUMBERS.length(); j++){
                if (symbol == NUMBERS.charAt(j)){
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
