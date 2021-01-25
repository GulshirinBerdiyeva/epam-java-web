package com.epam.task.first.logic;

import com.epam.task.first.entities.Array;

import java.util.List;

public class ArraySorter {

    public void insertSort(Array array){
        List<Integer> elements = array.getElements();
        for (int i = 1; i < elements.size(); i++){
            int element = elements.get(i);
            int j = i - 1;
            while(element < elements.get(j)){
                elements.set(j + 1, elements.get(j));
                j--;
                if (j < 0) {
                    break;
                }
            }
            elements.set(j + 1, element);
        }
    }

}
