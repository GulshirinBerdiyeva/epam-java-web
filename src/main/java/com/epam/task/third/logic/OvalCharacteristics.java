package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

public class OvalCharacteristics {

    private Boolean isXEquals(Point point1, Point point2){
        double x1 = point1.getX();
        double x2 = point2.getX();
        return x1 == x2;
    }

    private Boolean isYEquals(Point point1, Point point2){
        double y1 = point1.getY();
        double y2 = point2.getY();
        return y1 == y2;
    }

    public Boolean isOval(Oval oval){
        boolean isXEquals = isXEquals(oval.getPoint1(), oval.getPoint2());
        boolean isYEquals = isYEquals(oval.getPoint1(), oval.getPoint2());
        return !isXEquals && !isYEquals;
    }

    public Boolean isCircle(Oval oval){
        OvalParametersCalculator calculator = new OvalParametersCalculator();
        double a = calculator.calculateSideA(oval);
        double b = calculator.calculateSideB(oval);
        return a == b;
    }

    public Boolean isStraightLine(Oval oval){
        boolean isXEquals = isXEquals(oval.getPoint1(), oval.getPoint2());
        boolean isYEquals = isYEquals(oval.getPoint1(), oval.getPoint2());
        return isXEquals || isYEquals;
    }

    public Boolean isCrossesAxes(Oval oval){
        Point point1 = oval.getPoint1();
        Point point2 = oval.getPoint2();
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();

        boolean isX0 = x1 == 0 || x2 == 0 ;
        boolean isY0 = y1 == 0 || y2 == 0 ;
        if (isX0 || isY0){
            return true;
        }

        boolean isXDifferentSigned = (x1 > 0 && x2 < 0) || (x1 < 0 && x2 > 0);
        boolean isYDifferentSigned = (y1 > 0 && y2 < 0) || (y1 < 0 && y2 > 0);
        return isXDifferentSigned || isYDifferentSigned;
    }

}
