package com.epam.task.third.observe;

import com.epam.task.third.entity.Point;
import com.epam.task.third.parameters.OvalParameters;
import org.junit.Assert;
import org.junit.Test;

public class OvalObserverTest {
    private final static OvalObserver OBSERVER = OvalObserver.getOBSERVER();
    private final OvalObservable OVAL = new OvalObservable(42, new Point(5.0, 8.0), new Point(8.0, 5.0));

    @Test
    public void testUpdateShouldObservedChangesWhenOvalObservableElementApplied(){
        //when
        OVAL.addObserver(OBSERVER);

        OvalParameters actual = OBSERVER.getParametersMap(42);
        String oldParameters = actual.toString();
        OVAL.setPoint2(new Point(7.0, 7.0));
        OvalParameters actual2 = OBSERVER.getParametersMap(42);
        String newParameters = actual2.toString();

        //then
        Assert.assertNotEquals(oldParameters, newParameters);
    }

}
