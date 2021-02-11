package com.epam.task.fourth.entity;

public class PlantVisualParameters {
    private int length;
    private int floralParts;
    private String color;

    public PlantVisualParameters(int length, int floralParts, String color) {
        this.length = length;
        this.floralParts = floralParts;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlantVisualParameters)) return false;

        PlantVisualParameters that = (PlantVisualParameters) o;

        if (length != that.length) return false;
        if (floralParts != that.floralParts) return false;
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + floralParts;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractPlantVisualParameters{" +
                "length=" + length +
                ", floralParts=" + floralParts +
                ", color='" + color + '\'' +
                '}';
    }

}
