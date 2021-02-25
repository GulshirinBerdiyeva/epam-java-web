package com.epam.task.fifth.interpreter;

import org.junit.Assert;
import org.junit.Test;

public class ExpressionCalculatorTest {
    private final String EXPRESSION = "6 7 49 / * ";
    private final double EXPECTED = 42;
    private final double DELTA = 0.001;

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