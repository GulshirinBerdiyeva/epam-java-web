package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import org.junit.Assert;
import org.junit.Test;

public class OvalCharacteristicsTest {
    private OvalCharacteristics characteristics = new OvalCharacteristics();
    private final Oval OVAL1 = new Oval(42, new Point(0.0, 3.0), new Point(5.0, 5.0));
    private final Oval OVAL2 = new Oval(42, new Point(6.0, -2.0), new Point(8.0, -4.0));
    private final boolean EXPECTED_OVAL = true;
    private final boolean EXPECTED_CIRCLE = true;
    private final boolean EXPECTED_STRAIGHT_LINE = false;
    private final boolean EXPECTED_CROSS_AXES = false;

    @Test
    public void testIsOvalShouldReturnTrueWhenOvalApplied(){
        //when
        boolean actual = characteristics.isOval(OVAL1);

        //then
        Assert.assertEquals(EXPECTED_OVAL, actual);
    }

    @Test
    public void testIsCircleShouldReturnTrueWhenOvalApplied(){
        //when
        boolean actual = characteristics.isCircle(OVAL2);

        //then
        Assert.assertEquals(EXPECTED_CIRCLE, actual);
    }

    @Test
    public void testIsStraightLineShouldReturnFalseWhenOvalApplied(){
        //when
        boolean actual = characteristics.isStraightLine(OVAL1);

        //then
        Assert.assertEquals(EXPECTED_STRAIGHT_LINE, actual);
    }

    @Test
    public void testIsCrossesAxesShouldReturnFalseWhenOvalApplied(){
        //when
        boolean actual = characteristics.isCrossesAxes(OVAL2);

        //then
        Assert.assertEquals(EXPECTED_CROSS_AXES, actual);
    }

}
