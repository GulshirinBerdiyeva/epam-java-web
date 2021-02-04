package com.epam.task.third.entity;

public class Oval {
    private final Integer ID;
    private Point point1,
                  point2;

    public Oval(Integer ID, Point point1, Point point2) {
        this.ID = ID;
        this.point1 = point1;
        this.point2 = point2;
    }

    public Integer getID() {
        return ID;
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
        if (!(o instanceof Oval)) return false;

        Oval oval = (Oval) o;

        if (ID != null ? !ID.equals(oval.ID) : oval.ID != null) return false;
        if (point1 != null ? !point1.equals(oval.point1) : oval.point1 != null) return false;
        return point2 != null ? point2.equals(oval.point2) : oval.point2 == null;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (point1 != null ? point1.hashCode() : 0);
        result = 31 * result + (point2 != null ? point2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Oval{" +
                "ID=" + ID +
                ", point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }

}
