package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

public class OvalCharacteristicsCalculator {

    private boolean isXEquals(Point firstPoint, Point secondPoint){
        double firstX = firstPoint.getX();
        double secondX = secondPoint.getX();
        return firstX == secondX;
    }

    private boolean isYEquals(Point firstPoint, Point secondPoint){
        double firstY = firstPoint.getY();
        double secondY = secondPoint.getY();
        return firstY == secondY;
    }

    public boolean isOval(Oval oval){
        boolean isXEquals = isXEquals(oval.getFirstPoint(), oval.getSecondPoint());
        boolean isYEquals = isYEquals(oval.getFirstPoint(), oval.getSecondPoint());
        return !isXEquals && !isYEquals;
    }

    public boolean isCircle(Oval oval){
        OvalParametersCalculator calculator = new OvalParametersCalculator();
        double a = calculator.calculateSideA(oval);
        double b = calculator.calculateSideB(oval);
        return a == b;
    }

    public boolean isStraightLine(Oval oval){
        boolean isXEquals = isXEquals(oval.getFirstPoint(), oval.getSecondPoint());
        boolean isYEquals = isYEquals(oval.getFirstPoint(), oval.getSecondPoint());
        return isXEquals || isYEquals;
    }

    public boolean isCrossesAxes(Oval oval){
        Point point1 = oval.getFirstPoint();
        Point point2 = oval.getSecondPoint();
        double firstX = point1.getX();
        double firstY = point1.getY();
        double secondX = point2.getX();
        double secondY = point2.getY();

        boolean isX0 = firstX == 0 || secondX == 0 ;
        boolean isY0 = firstY == 0 || secondY == 0 ;
        if (isX0 || isY0){
            return true;
        }

        boolean isXDifferentSigned = (firstX > 0 && secondX < 0) || (firstX < 0 && secondX > 0);
        boolean isYDifferentSigned = (firstY > 0 && secondY < 0) || (firstY < 0 && secondY > 0);
        return isXDifferentSigned || isYDifferentSigned;
    }

}
