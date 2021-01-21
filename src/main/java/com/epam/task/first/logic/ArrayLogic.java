package com.epam.task.first.logic;

import com.epam.task.first.entities.Array;

public class ArrayLogic {

    public int findMax(Array array){
        int max = array.getElements().get(0);

        for (int newMax : array.getElements()){
            if (max < newMax){
                max = newMax;
            }
        }
        return max;
    }

    public int findMin(Array array){
        int min = array.getElements().get(0);

        for (int newMin : array.getElements()){
            if (min > newMin){
                min = newMin;
            }
        }
        return min;
    }
}