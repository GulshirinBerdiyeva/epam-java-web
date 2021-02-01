package com.epam.task.third.logic;

import com.epam.task.third.entity.Point;
import org.junit.Assert;
import org.junit.Test;

public class OvalCharacteristicsTest {
    OvalCharacteristics characteristics = new OvalCharacteristics();
    private final Point POINT1 = new Point(0.0, 3.0);
    private final Point POINT2 = new Point(5.0, 5.0);
    private final boolean EXPECTED_OVAL = true;
    private final Point POINT3 = new Point(6.0, -2.0);
    private final Point POINT4 = new Point(8.0, -4.0);
    private final boolean EXPECTED_CIRCLE = true;
    private final boolean EXPECTED_STRAIGHT_LINE = false;
    private final boolean EXPECTED_CROSS_AXES = true;

    @Test
    public void testIsOvalShouldReturnTrueWhen2PointsApplied(){
        //when
        boolean actual = characteristics.isOval(POINT1, POINT2);

        //then
        Assert.assertEquals(EXPECTED_OVAL, actual);
    }

    @Test
    public void testIsCircleShouldReturnTrueWhen2PointsApplied(){
        //when
        boolean actual = characteristics.isCircle(POINT3, POINT4);

        //then
        Assert.assertEquals(EXPECTED_CIRCLE, actual);
    }

    @Test
    public void testIsStraightLineShouldReturnFalseWhen2PointsApplied(){
        //when
        boolean actual = characteristics.isStraightLine(POINT3, POINT4);

        //then
        Assert.assertEquals(EXPECTED_STRAIGHT_LINE, actual);
    }

    @Test
    public void testIsCrossesAxesShouldReturnFalseWhen2PointsApplied(){
        //when
        boolean actual = characteristics.isCrossesAxes(POINT1, POINT2);

        //then
        Assert.assertEquals(EXPECTED_CROSS_AXES, actual);
    }

}
