package com.epam.task.third.sort;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

import java.util.Comparator;

public class OvalSortByXOfPoint1 implements Comparator<Oval> {

    public int compare(Oval o1, Oval o2) {
        Point point1 = o1.getPoint1();
        double x1 = point1.getX();
        Point point2 = o2.getPoint1();
        double x2 = point2.getX();
        return x1 > x2 ? 1 : x1 < x2 ? -1 : 0;
    }
}
