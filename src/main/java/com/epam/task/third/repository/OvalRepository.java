package com.epam.task.third.repository;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.sort.ISort;
import com.epam.task.third.specification.ISpecification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OvalRepository implements IRepository, ISort {
    private final static OvalRepository REPOSITORY = new OvalRepository();
    private List<Oval> list = new ArrayList<Oval>();

    private OvalRepository(){}

    public static OvalRepository getREPOSITORY() {
        return REPOSITORY;
    }

    public List<Oval> getList() {
        return list;
    }

    public void addOval(Oval oval) {
        list.add(oval);
    }

    public void removeOval(Oval oval) {
        list.remove(oval);
    }

    public void updateOval(Oval newOval) {
        int ID = newOval.getID();
        for (Oval oval : list){
            if (ID == oval.getID()){
                list.remove(oval);
                list.add(newOval);
            }
        }
    }

    public List<Oval> query(ISpecification specification) {
        List<Oval> newList = new ArrayList<Oval>();
        for (Oval oval : list){
            if (specification.specified(oval)){
                newList.add(oval);
            }
        }
        return newList;
    }

    public void sort(Comparator comparator) {
        list.sort(comparator);
    }
}
