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

}
