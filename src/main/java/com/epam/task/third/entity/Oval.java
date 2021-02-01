package com.epam.task.third.entity;

import com.epam.task.third.logic.OvalParameters;

public class Oval {
    private Point point1,
                  point2;
    private OvalParameters parameters = new OvalParameters();

    public Oval(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public OvalParameters getParameters() {
        return parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oval)) return false;

        Oval oval = (Oval) o;

        if (point1 != null ? !point1.equals(oval.point1) : oval.point1 != null) return false;
        if (point2 != null ? !point2.equals(oval.point2) : oval.point2 != null) return false;
        return parameters != null ? parameters.equals(oval.parameters) : oval.parameters == null;
    }

    @Override
    public int hashCode() {
        int result = point1 != null ? point1.hashCode() : 0;
        result = 31 * result + (point2 != null ? point2.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Oval{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", parameters=" + parameters +
                '}';
    }

}
