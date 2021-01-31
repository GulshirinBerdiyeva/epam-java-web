package com.epam.task.third.entity;

public abstract class AbstractFigure {
    private Point point1,
                  point2;

    public AbstractFigure(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFigure)) return false;

        AbstractFigure that = (AbstractFigure) o;

        if (point1 != null ? !point1.equals(that.point1) : that.point1 != null) return false;
        return point2 != null ? point2.equals(that.point2) : that.point2 == null;
    }

    @Override
    public int hashCode() {
        int result = point1 != null ? point1.hashCode() : 0;
        result = 31 * result + (point2 != null ? point2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractFigure{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }

}
