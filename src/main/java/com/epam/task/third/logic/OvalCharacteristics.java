package com.epam.task.third.logic;

import com.epam.task.third.entity.Point;

public class OvalCharacteristics {

    private boolean isXEquals(Point point1, Point point2){
        double x1 = point1.getX();
        double x2 = point2.getX();
        return x1 == x2;
    }

    private boolean isYEquals(Point point1, Point point2){
        double y1 = point1.getY();
        double y2 = point2.getY();
        return y1 == y2;
    }

    private boolean isCross(Point point1, Point point2){
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
        if (isXDifferentSigned || isYDifferentSigned){
            return true;
        }
        return false;
    }

    public Boolean isOval(Point point1, Point point2){
        boolean isXEquals = isXEquals(point1, point2);
        boolean isYEquals = isYEquals(point1, point2);

        return !isXEquals && !isYEquals;
    }

    public Boolean isCircle(Point point1, Point point2){
        OvalParameters parameters = new OvalParameters();
        parameters.calculateSide(point1, point2);
        double a = parameters.getA();
        double b = parameters.getB();

        return a == b;
    }

    public Boolean isStraightLine(Point point1, Point point2){
        boolean isXEquals = isXEquals(point1, point2);
        boolean isYEquals = isYEquals(point1, point2);

        return isXEquals || isYEquals;
    }

    public Boolean isCrossesAxes(Point point1, Point point2){
        return isCross(point1, point2);
    }

}
