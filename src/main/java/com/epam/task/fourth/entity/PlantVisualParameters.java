package com.epam.task.fourth.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class PlantVisualParameters {
    @XmlElement(namespace = "http://www.example.com/orangery")
    private String color;
    @XmlElement(name = "floral-parts", namespace = "http://www.example.com/orangery")
    private int floralParts;
    @XmlElement(namespace = "http://www.example.com/orangery")
    private int length;

    public PlantVisualParameters() {
        super();
    }

    public PlantVisualParameters(String color, int floralParts, int length) {
        this.color = color;
        this.floralParts = floralParts;
        this.length = length;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFloralParts() {
        return floralParts;
    }

    public void setFloralParts(int floralParts) {
        this.floralParts = floralParts;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

        if (floralParts != that.floralParts) {
            return false;
        }
        if (length != that.length) {
            return false;
        }
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + floralParts;
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        return "\n\tcolor: " + color +
                "\n\tfloral-parts: " + floralParts +
                "\n\tlength: " + length;
    }

}