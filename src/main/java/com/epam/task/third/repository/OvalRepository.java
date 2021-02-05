package com.epam.task.third.repository;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.sort.ISort;
import com.epam.task.third.specification.ISpecification;

import java.util.Comparator;
import java.util.List;

public class OvalRepository implements IRepository, ISort {
    public void addOval(Oval oval) {

    }

    public void removeOval(Oval oval) {

    }

    public void updateOval(Oval oval) {

    }

    public List<Oval> query(ISpecification specification) {
        return null;
    }

    public void sort(Comparator comparator) {

    }
}
