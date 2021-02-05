package com.epam.task.third.sort;

import com.epam.task.third.entity.Oval;

import java.util.Comparator;

public class OvalSortByID implements Comparator<Oval> {

    public int compare(Oval o1, Oval o2) {
        int ID1 = o1.getID();
        int ID2 = o2.getID();
        return ID1 > ID2 ? 1 : ID1 < ID2 ? -1 : 0;
    }
}
