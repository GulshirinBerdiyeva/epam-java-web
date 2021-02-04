package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import org.junit.Assert;
import org.junit.Test;

public class OvalParametersCalculatorTest {
    private OvalParametersCalculator calculator = new OvalParametersCalculator();
    private final Oval OVAL = new Oval(42, new Point(0.0, 3.0), new Point(5.0, 5.0));
    private final double EXPECTED_P = 11.963;
    private final double EXPECTED_A = 7.854;
    private final double DELTA = 0.001;

    @Test
    public void testCalculatePerimeterShouldReturnPerimeterWhenOvalApplied(){
        //when
        double actual = calculator.calculatePerimeter(OVAL);

        //then
        Assert.assertEquals(EXPECTED_P, actual, DELTA);
    }

    @Test
    public void testCalculateAreaShouldReturnAreaWhenOvalApplied(){
        //when
        double actual = calculator.calculateArea(OVAL);

        //then
        Assert.assertEquals(EXPECTED_A, actual, DELTA);
    }

}
