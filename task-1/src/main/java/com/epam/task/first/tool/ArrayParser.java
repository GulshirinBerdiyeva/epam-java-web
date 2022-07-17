package com.epam.task.first.tool;

import com.epam.task.first.entity.Array;

import java.util.ArrayList;
import java.util.List;

public class ArrayParser {

    public Array parse(String line){
        List<Integer> array = new ArrayList<Integer>();
        String[] stringArray = line.split(" ");

        for (int i = 0; i < stringArray.length; i++){
            if (stringArray[i].length() != 0) {
                array.add(Integer.parseInt(stringArray[i]));
            }
        }
        Array result = new Array(array);
        return result;
    }

}
