package com.epam.task.first.logic;

import com.epam.task.first.entities.Array;
import org.junit.Assert;
import org.junit.Test;

public class ArrayLogicTest {

    private ArrayLogic arrayLogic = new ArrayLogic();

    //FindMax
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

    //FindMin
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

}
