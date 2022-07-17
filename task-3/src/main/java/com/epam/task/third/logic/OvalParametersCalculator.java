package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

public class OvalParametersCalculator {

    public Double calculateSideA(Oval oval) {
        Point firstPoint = oval.getFirstPoint();
        Point secondPoint = oval.getSecondPoint();
        double firstY = firstPoint.getY();
        double secondY = secondPoint.getY();

        double difference = Math.abs(firstY) - Math.abs(secondY);
        double length = Math.sqrt(Math.pow(difference, 2)) / 2.;
        return length;
    }

    public Double calculateSideB(Oval oval) {
        Point firstPoint = oval.getFirstPoint();
        Point secondPoint = oval.getSecondPoint();
        double firstX = firstPoint.getX();
        double secondX = secondPoint.getX();

        double difference = Math.abs(firstX) - Math.abs(secondX);
        double length = Math.sqrt(Math.pow(difference, 2)) / 2.;
        return length;
    }

    public Double calculatePerimeter(Oval oval) {
        double a = calculateSideA(oval);
        double b = calculateSideB(oval);
        return 2 * Math.PI * Math.sqrt( (Math.pow(a, 2) + Math.pow(b, 2)) / 2);
    }

    public Double calculateArea(Oval oval) {
        double a = calculateSideA(oval);
        double b = calculateSideB(oval);
        return Math.PI * a * b;
    }

}