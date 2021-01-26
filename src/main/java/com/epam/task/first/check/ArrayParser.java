package com.epam.task.first.check;

import com.epam.task.first.entities.Array;

import java.util.ArrayList;
import java.util.List;

public class ArrayParser {

    public Array parse(String line){
        List<Integer> array = new ArrayList<Integer>();
        String[] s = line.split(" ");

        for (int i = 0; i < s.length; i++){
            if (s[i].length() != 0) {
                array.add(Integer.parseInt(s[i]));
            }
        }
        Array result = new Array(array);
        return result;
    }

}
