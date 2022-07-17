package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import org.junit.Assert;
import org.junit.Test;

public class OvalCharacteristicsCalculatorTest {
    private OvalCharacteristicsCalculator characteristics = new OvalCharacteristicsCalculator();
    private final static Oval FIRST_OVAL = new Oval(42, new Point(0.0, 3.0),
                                                            new Point(5.0, 5.0));
    private final static Oval SECOND_OVAL = new Oval(24, new Point(6.0, -2.0),
                                                             new Point(8.0, -4.0));
    private final boolean expectedOval = true;
    private final boolean expectedCircle = true;
    private final boolean expectedStraightLine = false;
    private final boolean expectedCrossAxes = false;

    @Test
    public void testIsOvalShouldReturnTrueWhenOvalApplied(){
        //when
        boolean actual = characteristics.isOval(FIRST_OVAL);

        //then
        Assert.assertEquals(expectedOval, actual);
    }

    @Test
    public void testIsCircleShouldReturnTrueWhenOvalApplied(){
        //when
        boolean actual = characteristics.isCircle(SECOND_OVAL);

        //then
        Assert.assertEquals(expectedCircle, actual);
    }

    @Test
    public void testIsStraightLineShouldReturnFalseWhenOvalApplied(){
        //when
        boolean actual = characteristics.isStraightLine(FIRST_OVAL);

        //then
        Assert.assertEquals(expectedStraightLine, actual);
    }

    @Test
    public void testIsCrossesAxesShouldReturnFalseWhenOvalApplied(){
        //when
        boolean actual = characteristics.isCrossesAxes(SECOND_OVAL);

        //then
        Assert.assertEquals(expectedCrossAxes, actual);
    }

}