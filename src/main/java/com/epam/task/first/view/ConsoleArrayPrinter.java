package com.epam.task.first.view;

import com.epam.task.first.entities.Array;

import java.util.List;

public class ConsoleArrayPrinter implements ArrayPrinter{

    public void print(final Array array) {
        List<Integer> elements = array.getElements();

        for (Integer element : elements){
            System.out.print(element + " ");
        }
    }

}
