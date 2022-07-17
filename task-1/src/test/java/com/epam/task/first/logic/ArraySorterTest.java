package com.epam.task.first.logic;

import com.epam.task.first.entity.Array;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Assert;
import org.junit.Test;

public class ArraySorterTest {
    private ArraySorter arraySorter = new ArraySorter();
    private ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
    private final Array ACTUAL_ARRAY = new Array(4, -5, 8, 0, -9, 4);
    private final Array EXPECTED_ARRAY = new Array(-9, -5, 0, 4, 4, 8 );

    @Test
    public void testInsertSortShouldSortArrayByAscendingWhenUnsortedArrayApplied(){
        //when
        arraySorter.insertSort(ACTUAL_ARRAY);

        //then
        Assert.assertEquals(EXPECTED_ARRAY, ACTUAL_ARRAY);
        printer.print(EXPECTED_ARRAY);
        printer.print(ACTUAL_ARRAY);
    }

    @Test
    public void testBubbleSortShouldSortArrayByAscendingWhenUnsortedArrayApplied(){
        //when
        arraySorter.bubbleSort(ACTUAL_ARRAY);

        //then
        Assert.assertEquals(EXPECTED_ARRAY, ACTUAL_ARRAY);
        printer.print(EXPECTED_ARRAY);
        printer.print(ACTUAL_ARRAY);
    }

}
