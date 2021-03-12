package com.epam.task.third.specification;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.logic.OvalParametersCalculator;

public class OvalSpecificationByPerimeter implements Specification {
    private double perimeter;
    private OvalParametersCalculator calculator = new OvalParametersCalculator();

    public OvalSpecificationByPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public Boolean specified(Oval oval) {
        double perimeter = calculator.calculatePerimeter(oval);
        return perimeter <= this.perimeter ;
    }

}