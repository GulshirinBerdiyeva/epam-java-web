package com.epam.task.third.sort;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

import java.util.Comparator;

public class SortOvalFirstPointX implements Comparator<Oval> {

    public int compare(Oval firstOval, Oval secondOval) {
        Point firstPoint = firstOval.getFirstPoint();
        double firstX = firstPoint.getX();
        Point secondPoint = secondOval.getFirstPoint();
        double secondX = secondPoint.getX();
        return firstX > secondX ? 1 : firstX < secondX ? -1 : 0;
    }

}