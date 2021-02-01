package com.epam.task.third.logic;

import com.epam.task.third.entity.Point;

import static java.lang.Math.*;

public class OvalParameters {
    private double a,
                   b,
                   perimeter,
                   area;

    public void calculateSide(Point point1, Point point2) {
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();
        double x3 = point1.getX();
        double y3 = point2.getY();

        double x1x3 = abs(x3) - abs(x1);
        double y1y3 = abs(y3) - abs(y1);
        a = sqrt(pow(x1x3, 2) + pow(y1y3, 2)) / 2.;

        double x2x3 = abs(x3) - abs(x2);
        double y2y3 = abs(y3) - abs(y2);
        b = sqrt(pow(x2x3, 2) + pow(y2y3, 2)) / 2.;
    }

    public Double calculatePerimeter() {
        this.perimeter =2 * PI * sqrt( (pow(a, 2) + pow(b, 2)) / 2);
        return this.perimeter;
    }

    public Double calculateArea() {
        this.area =PI * a * b;
        return this.area;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OvalParameters)) return false;

        OvalParameters that = (OvalParameters) o;

        if (Double.compare(that.a, a) != 0) return false;
        if (Double.compare(that.b, b) != 0) return false;
        if (Double.compare(that.perimeter, perimeter) != 0) return false;
        return Double.compare(that.area, area) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(perimeter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(area);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OvalParameters{" +
                "a=" + a +
                ", b=" + b +
                ", perimeter=" + perimeter +
                ", area=" + area +
                '}';
    }

}
