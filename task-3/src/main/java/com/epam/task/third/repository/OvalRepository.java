package com.epam.task.third.repository;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.sort.Sorter;
import com.epam.task.third.specification.Specification;

import java.util.*;

public class OvalRepository implements Repository, Sorter {
    private final static OvalRepository INSTANCE = new OvalRepository();

    private Map<Integer, Oval> ovalsMap = new HashMap<Integer, Oval>();

    private OvalRepository(){}

    public static OvalRepository getInstance() {
        return INSTANCE;
    }

    public List<Oval> getList() {
        List<Oval> ovals = new ArrayList<Oval>(ovalsMap.values());
        return ovals;
    }

    public void addOval(Oval oval) {
        ovalsMap.put(oval.getId(), oval);
    }

    public void removeOval(Oval oval) {
        ovalsMap.remove(oval.getId());
    }

    public void updateOval(Oval newOval) {
        int id = newOval.getId();
        if (ovalsMap.containsKey(id)){
            ovalsMap.remove(id);
            ovalsMap.put(id, newOval);
        }
    }

    public List<Oval> query(Specification specification) {
        List<Oval> newList = new ArrayList<Oval>();

        for (Map.Entry<Integer, Oval> oval : ovalsMap.entrySet()){
            if (specification.specified(oval.getValue())){
                newList.add(oval.getValue());
            }
        }

        return newList;
    }

    public List<Oval> sort(Comparator comparator) {
        List<Oval> ovals = new ArrayList<Oval>(ovalsMap.values());
        ovals.sort(comparator);
        return ovals;
    }

}