package com.epam.task.first.entity;

import java.util.Arrays;
import java.util.List;

public class Array {
   private final List<Integer> ELEMENTS;

    public Array(Integer... elements) {
        this.ELEMENTS = Arrays.asList(elements);
    }

    public Array(List<Integer> elements) {
        this.ELEMENTS = elements;
    }

    public List<Integer> getElements() {
        return ELEMENTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Array)) {
            return false;
        }

        Array array = (Array) o;

        return ELEMENTS != null ? ELEMENTS.equals(array.ELEMENTS) : array.ELEMENTS == null;
    }

    @Override
    public int hashCode() {
        return ELEMENTS != null ? ELEMENTS.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Array{" +
                "ELEMENTS=" + ELEMENTS +
                '}';
    }

}
