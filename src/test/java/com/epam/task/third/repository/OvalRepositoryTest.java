package com.epam.task.third.repository;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import com.epam.task.third.sort.OvalSortByID;
import com.epam.task.third.sort.OvalSortByXOfPoint1;
import com.epam.task.third.specification.OvalSpecificationByArea;
import com.epam.task.third.specification.OvalSpecificationByPerimeter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class OvalRepositoryTest {
    private final static OvalRepository REPOSITORY = OvalRepository.getREPOSITORY();
    private static final List<Oval> OVAL_LIST = Arrays.asList(new Oval(42, new Point(5.0, 3.0), new Point(0.0, 5.0)),
                                                       new Oval(18, new Point(-6.0, 2.5), new Point(3.0, 4.0)),
                                                       new Oval(20, new Point(-2.0, -1.0), new Point(1.0, -5.0)),
                                                       new Oval(7, new Point(7.0, 3.5), new Point(-2.0, 5.5)));
    private final List<Oval> EXPECTED_PERIMETER = Arrays.asList(new Oval(18, new Point(-6.0, 2.5), new Point(3.0, 4.0)),
                                                                new Oval(20, new Point(-2.0, -1.0), new Point(1.0, -5.0)));
    private final List<Oval> EXPECTED_AREA = Arrays.asList(new Oval(7, new Point(7.0, 3.5), new Point(-2.0, 5.5)),
                                                           new Oval(42, new Point(5.0, 3.0), new Point(0.0, 5.0)));
    private final List<Oval> EXPECTED_ID = Arrays.asList(new Oval(7, new Point(7.0, 3.5), new Point(-2.0, 5.5)),
                                                         new Oval(18, new Point(-6.0, 2.5), new Point(3.0, 4.0)),
                                                         new Oval(20, new Point(-2.0, -1.0), new Point(1.0, -5.0)),
                                                         new Oval(42, new Point(5.0, 3.0), new Point(0.0, 5.0)));
    private final List<Oval> EXPECTED_XOfPoint1 = Arrays.asList(new Oval(18, new Point(-6.0, 2.5), new Point(3.0, 4.0)),
                                                                new Oval(20, new Point(-2.0, -1.0), new Point(1.0, -5.0)),
                                                                new Oval(42, new Point(5.0, 3.0), new Point(0.0, 5.0)),
                                                                new Oval(7, new Point(7.0, 3.5), new Point(-2.0, 5.5)));

    @BeforeClass
    public static void add(){
        //given
        for (Oval oval : OVAL_LIST){
            REPOSITORY.addOval(oval);
        }
    }

    @Test
    public void testQueryShouldReturnListSpecifiedByPerimeterWhenOvalsApplied(){
        //when
        OvalSpecificationByPerimeter byPerimeter = new OvalSpecificationByPerimeter(10.);
        List<Oval> actual = REPOSITORY.query(byPerimeter);

        //then
        Assert.assertEquals(EXPECTED_PERIMETER, actual);
    }

    @Test
    public void testQueryShouldReturnListSpecifiedByAreaWhenOvalsApplied(){
        //when
        OvalSpecificationByArea byArea = new OvalSpecificationByArea(4.);
        List<Oval> actual = REPOSITORY.query(byArea);

        //then
        Assert.assertEquals(EXPECTED_AREA, actual);
    }

    @Test
    public void testQueryShouldReturnListSortedByIDWhenOvalsApplied(){
        //when
        OvalSortByID byID = new OvalSortByID();
        REPOSITORY.sort(byID);
        List<Oval> actual = REPOSITORY.getList();

        //then
        Assert.assertEquals(EXPECTED_ID, actual);
    }

    @Test
    public void testQueryShouldReturnListSortedByXOfPoint1WhenOvalsApplied(){
        //when
        OvalSortByXOfPoint1 xOfPoint1 = new OvalSortByXOfPoint1();
        REPOSITORY.sort(xOfPoint1);
        List<Oval> actual = REPOSITORY.getList();

        //then
        Assert.assertEquals(EXPECTED_XOfPoint1, actual);
    }

}
