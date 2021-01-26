package com.epam.task.first.logic;

import com.epam.task.first.entities.Array;
import com.epam.task.first.view.ConsoleArrayPrinter;
import org.junit.Assert;
import org.junit.Test;

public class ArrayLogicTest {

    private ArrayLogic arrayLogic = new ArrayLogic();

    //findMax
    @Test
    public void testFindMaxShouldFindMaxWhenPositiveNumbersApplied(){
        //given
        Array array = new Array(7, 18, 5);

        //when
        int actual = arrayLogic.findMax(array);

        //then
        Assert.assertEquals(18, actual);
    }

    @Test
    public void testFindMaxShouldFindMaxWhenNegativeNumbersApplied(){
        //given
        Array array = new Array(-7, -18, -5);

        //when
        int actual = arrayLogic.findMax(array);

        //then
        Assert.assertEquals(-5, actual);
    }

    //findMin
    @Test
    public void testFindMinShouldFindMinWhenPositiveNumbersApplied(){
        //given
        Array array = new Array(7, 18, 5);

        //when
        int actual = arrayLogic.findMin(array);

        //then
        Assert.assertEquals(5, actual);
    }

    @Test
    public void testFindMinShouldFindMinWhenDifferentSignedNumbersApplied(){
        //given
        Array array = new Array(7, -18, 5, -7, 2);

        //when
        int actual = arrayLogic.findMin(array);

        //then
        Assert.assertEquals(-18, actual);
    }

    //replacementEvenElements
    @Test
    public void testReplacementEvenElementsShouldReplacementEvenElementsWhenNumbersApplied(){
        //given
        Array array = new Array(1, 2, 3, 4, 12, 11, 10, 9);
        ConsoleArrayPrinter printer = new ConsoleArrayPrinter();
        printer.print(array);

        //when
        arrayLogic.replacementEvenElements(array);

        //then
        printer.print(array);
    }

    //findMean
    @Test
    public void testFindMeanShouldFindMeanWhenNumbersApplied(){
        //given
        Array array = new Array(1, 2, 3, 4, 12, 11, 10, 9);

        //when
        double actual = arrayLogic.findMean(array);

        //then
        Assert.assertEquals(6.5, actual, 0.001);
    }

    //findSum
    @Test
    public void testFindSumShouldFindSumWhenNumbersApplied(){
        //given
        Array array = new Array(1, 2, 3, 4, 12, 11, 10, 9);

        //when
        int actual = arrayLogic.findSum(array);

        //then
        Assert.assertEquals(52, actual);
    }

    //findAmountOfPositiveElements
    @Test
    public void testFindAmountOfPositiveElementsShouldFindAmountOfPositiveElementsWhenNumbersApplied(){
        //given
        Array array = new Array(-1, -2, 3, -4, 12, -11, 10, -9);

        //when
        int actual = arrayLogic.findAmountOfPositiveElements(array);

        //then
        Assert.assertEquals(3, actual);
    }

    //findAmountOfNegativeElements
    @Test
    public void testFindAmountOfNegativeElementsShouldFindAmountOfNegativeElementsWhenNumbersApplied(){
        //given
        Array array = new Array(-1, -2, 3, 4, 12, -11, 10, -9);

        //when
        int actual = arrayLogic.findAmountOfNegativeElements(array);

        //then
        Assert.assertEquals(4, actual);
    }

}
