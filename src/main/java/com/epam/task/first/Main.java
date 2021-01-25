package com.epam.task.first;

import com.epam.task.first.entities.Array;
import com.epam.task.first.view.IArrayPrinter;
import com.epam.task.first.view.ConsoleArrayPrinter;

public class Main {

    public static void main(String[] args) {
        Array array = new Array(1, 2, 3);

        ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
        printer.print(array);
    }

}
