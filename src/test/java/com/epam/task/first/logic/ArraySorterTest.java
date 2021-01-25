package com.epam.task.first.logic;

import com.epam.task.first.entities.Array;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Test;

public class ArraySorterTest {

    private ArraySorter arraySorter = new ArraySorter();

    @Test
    public void testInsertSortShouldSortArrayByAscendingWhenUnsortedArrayApplied(){
        //given
        Array array = new Array(4, -5, 8, 0, -9, 4);
        ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
        printer.print(array);

        //when
        arraySorter.insertSort(array);

        //then
        printer.print(array);
    }

    @Test
    public void testBubbleSortShouldSortArrayByAscendingWhenUnsortedArrayApplied(){
        //given
        Array array = new Array(4, -5, 8, 0, -9, 4);
        ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
        printer.print(array);

        //when
        arraySorter.bubbleSort(array);

        //then
        printer.print(array);
    }

}
