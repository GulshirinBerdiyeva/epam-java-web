package com.epam.task.third.sort;

import com.epam.task.third.entity.Oval;

import java.util.Comparator;
import java.util.List;

public interface Sorter {
    List<Oval> sort(Comparator comparator);
}