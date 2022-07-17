package com.epam.task.third.logic;

import java.util.HashSet;
import java.util.Set;

public class IdGenerator {
    private Set<Integer> ids = new HashSet<Integer>();

    public int generateId(){
        int id;
        do {
            id = (int) (Math.random() * 42);
        } while (!ids.add(id));

        return id;
    }

}