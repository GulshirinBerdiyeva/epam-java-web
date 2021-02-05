package com.epam.task.third.repository;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.specification.ISpecification;

import java.util.List;

public interface IRepository {
    void addOval(Oval oval);
    void removeOval(Oval oval);
    void updateOval(Oval oval);
    List<Oval> query(ISpecification specification);
}
