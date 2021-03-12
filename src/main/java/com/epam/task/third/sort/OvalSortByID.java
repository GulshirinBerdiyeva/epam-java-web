package com.epam.task.third.sort;

import com.epam.task.third.entity.Oval;

import java.util.Comparator;

public class OvalSortByID implements Comparator<Oval> {

    public int compare(Oval firstOval, Oval secondOval) {
        int firstId = firstOval.getId();
        int secondId = secondOval.getId();
        return firstId > secondId ? 1 : firstId < secondId ? -1 : 0;
    }

}