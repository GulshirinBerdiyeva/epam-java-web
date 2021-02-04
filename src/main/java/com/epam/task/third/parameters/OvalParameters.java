package com.epam.task.third.parameters;

public class OvalParameters {
    private double a,
                   b,
                   perimeter,
                   area;

    public OvalParameters(double a, double b, double perimeter, double area) {
        this.a = a;
        this.b = b;
        this.perimeter = perimeter;
        this.area = area;
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
