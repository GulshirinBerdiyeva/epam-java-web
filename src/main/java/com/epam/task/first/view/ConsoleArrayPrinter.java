package com.epam.task.first.view;

import com.epam.task.first.entity.Array;

import java.util.List;

public class ConsoleArrayPrinter implements IArrayPrinter{

    public void print(final Array array) {
        List<Integer> elements = array.getElements();
        for (Integer element : elements){
            System.out.print(element + " ");
        }
        System.out.println();
    }

}
