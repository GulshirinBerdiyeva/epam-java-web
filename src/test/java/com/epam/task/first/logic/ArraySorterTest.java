package com.epam.task.first.logic;

import com.epam.task.first.entity.Array;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Assert;
import org.junit.Test;

public class ArraySorterTest {
    private ArraySorter arraySorter = new ArraySorter();
    private ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
    private final Array actualArray = new Array(4, -5, 8, 0, -9, 4);
    private final Array expectedArray = new Array(-9, -5, 0, 4, 4, 8 );

    @Test
    public void testInsertSortShouldSortArrayByAscendingWhenUnsortedArrayApplied(){
        //when
        arraySorter.insertSort(actualArray);

        //then
        Assert.assertEquals(expectedArray, actualArray);
        printer.print(expectedArray);
        printer.print(actualArray);
    }

    @Test
    public void testBubbleSortShouldSortArrayByAscendingWhenUnsortedArrayApplied(){
        //when
        arraySorter.bubbleSort(actualArray);

        //then
        Assert.assertEquals(expectedArray, actualArray);
        printer.print(expectedArray);
        printer.print(actualArray);
    }

}
