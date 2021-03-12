package com.epam.task.third.entity;

public class Oval {
    private final int id;
    private Point firstPoint;
    private Point secondPoint;

    public Oval(int id, Point firstPoint, Point secondPoint) {
        this.id = id;
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    public int getId() {
        return id;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Oval)) {
            return false;
        }

        Oval oval = (Oval) o;

        if (id != oval.id) {
            return false;
        }
        if (firstPoint != null ? !firstPoint.equals(oval.firstPoint) : oval.firstPoint != null) {
            return false;
        }
        return secondPoint != null ? secondPoint.equals(oval.secondPoint) : oval.secondPoint == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstPoint != null ? firstPoint.hashCode() : 0);
        result = 31 * result + (secondPoint != null ? secondPoint.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Oval{" +
                "id=" + id +
                ", firstPoint=" + firstPoint +
                ", secondPoint=" + secondPoint +
                '}';
    }

}