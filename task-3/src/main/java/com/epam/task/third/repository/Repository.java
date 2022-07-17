package com.epam.task.third.repository;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.specification.Specification;

import java.util.List;

public interface Repository {
    void addOval(Oval oval);
    void removeOval(Oval oval);
    void updateOval(Oval oval);
    List<Oval> query(Specification specification);
}