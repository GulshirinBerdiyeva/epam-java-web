package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import org.junit.Assert;
import org.junit.Test;

public class OvalParametersCalculatorTest {
    private OvalParametersCalculator calculator = new OvalParametersCalculator();
    private final static Oval OVAL = new Oval(42, new Point(0.0, 3.0),
                                                      new Point(5.0, 5.0));
    private final double expectedPerimeter = 11.963;
    private final double expectedArea = 7.854;
    private final double delta = 0.001;

    @Test
    public void testCalculatePerimeterShouldReturnPerimeterWhenOvalApplied(){
        //when
        double actual = calculator.calculatePerimeter(OVAL);

        //then
        Assert.assertEquals(expectedPerimeter, actual, delta);
    }

    @Test
    public void testCalculateAreaShouldReturnAreaWhenOvalApplied(){
        //when
        double actual = calculator.calculateArea(OVAL);

        //then
        Assert.assertEquals(expectedArea, actual, delta);
    }

}