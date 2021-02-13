package com.epam.task.fourth.entity;

public class PlantVisualParameters {
    private String color;
    private int length;
    private int floralParts;

    public PlantVisualParameters(String color, int length, int floralParts) {
        this.color = color;
        this.length = length;
        this.floralParts = floralParts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFloralParts() {
        return floralParts;
    }

    public void setFloralParts(int floralParts) {
        this.floralParts = floralParts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlantVisualParameters)) {
            return false;
        }

        PlantVisualParameters that = (PlantVisualParameters) o;

        if (length != that.length) {
            return false;
        }
        if (floralParts != that.floralParts) {
            return false;
        }
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + length;
        result = 31 * result + floralParts;
        return result;
    }

    @Override
    public String toString() {
        return "PlantVisualParameters{" +
                "color='" + color + '\'' +
                ", length=" + length +
                ", floralParts=" + floralParts +
                '}';
    }

}
