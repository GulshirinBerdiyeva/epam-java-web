package com.epam.task.third.specification;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.logic.OvalParametersCalculator;

public class OvalSpecificationByArea implements ISpecification {
    private double area;
    private OvalParametersCalculator calculator = new OvalParametersCalculator();

    public OvalSpecificationByArea(double area) {
        this.area = area;
    }

    public Boolean specified(Oval oval) {
        double area = calculator.calculateArea(oval);
        return area >= this.area ;
    }
}
