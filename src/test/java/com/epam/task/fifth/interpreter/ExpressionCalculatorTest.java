package com.epam.task.fifth.interpreter;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionCalculatorTest {
    private final static String EXPRESSION = "6 7 49 / * ";
    private final static double EXPECTED = 42;
    private final static double DELTA = 0.001;

    @Test
    public void testCalculateShouldReturnExpressionResultWhenExpressionStringApplied(){
        //given
        ExpressionCalculator calculator = new ExpressionCalculator();

        //when
        double actual = (double) calculator.calculate(EXPRESSION);

        //then
        Assert.assertEquals(EXPECTED, actual, DELTA);
    }

}