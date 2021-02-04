package com.epam.task.third.logic;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;

import static java.lang.Math.*;

public class OvalParametersCalculator {

    public Double calculateSideA(Oval oval) {
        Point point1 = oval.getPoint1();
        Point point2 = oval.getPoint2();
        double y1 = point1.getY();
        double y2 = point2.getY();

        double y1y2 = abs(y1) - abs(y2);
        return sqrt(pow(y1y2, 2)) / 2.;
    }

    public Double calculateSideB(Oval oval) {
        Point point1 = oval.getPoint1();
        Point point2 = oval.getPoint2();
        double x1 = point1.getX();
        double x2 = point2.getX();

        double x1x2 = abs(x1) - abs(x2);
        return sqrt(pow(x1x2, 2)) / 2.;
    }

    public Double calculatePerimeter(Oval oval) {
        double a = calculateSideA(oval);
        double b = calculateSideB(oval);
        return 2 * PI * sqrt( (pow(a, 2) + pow(b, 2)) / 2);
    }

    public Double calculateArea(Oval oval) {
        double a = calculateSideA(oval);
        double b = calculateSideB(oval);
        return PI * a * b;
    }

}
