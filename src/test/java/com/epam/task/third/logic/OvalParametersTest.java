package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import org.junit.Assert;
import org.junit.Test;

public class OvalParametersTest {
    private OvalParameters ovalParameter = new OvalParameters();
    private final Point POINT1 = new Point(0.0, 3.0);
    private final Point POINT2 = new Point(5.0, 5.0);
    private final double EXPECTED_P = 11.963;
    private final double EXPECTED_A = 7.854;
    private final double DELTA = 0.001;

    @Test
    public void testCalculatePerimeterShouldReturnPerimeterWhenOvalApplied(){
        //when
        ovalParameter.calculateSide(POINT1,POINT2);
        double actual = ovalParameter.calculatePerimeter();

        //then
        Assert.assertEquals(EXPECTED_P, actual, DELTA);
    }

    @Test
    public void testCalculateAreaShouldReturnAreaWhenOvalApplied(){
        //when
        ovalParameter.calculateSide(POINT1,POINT2);
        double actual = ovalParameter.calculateArea();

        //then
        Assert.assertEquals(EXPECTED_A, actual, DELTA);
    }

}
