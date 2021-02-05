package com.epam.task.third.observe;

import com.epam.task.third.entity.Point;
import com.epam.task.third.parameters.OvalParameters;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class OvalObserverTest {
    private final static OvalObserver OBSERVER = OvalObserver.getOBSERVER();
    private final OvalObservable OVAL = new OvalObservable(42, new Point(5.0, 8.0), new Point(8.0, 5.0));

    @Test
    public void testUpdateShouldObservedChangesWhenOvalObservableElementApplied(){
        //when
        OVAL.addObserver(OBSERVER);

        Map<Integer, OvalParameters> actual = OBSERVER.getParametersMap();
        String oldParameters = actual.toString();
        OVAL.setPoint2(new Point(7.0, 7.0));
        Map<Integer, OvalParameters> actual2 = OBSERVER.getParametersMap();
        String newParameters = actual2.toString();

        //then
        Assert.assertNotEquals(oldParameters, newParameters);
    }

}
