package com.epam.task.third.observe;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.logic.OvalParametersCalculator;
import com.epam.task.third.parameters.OvalParameters;

import java.util.HashMap;
import java.util.Map;

public class OvalObserver implements IObserver {
    private final static OvalObserver OBSERVER = new OvalObserver();
    private OvalParametersCalculator calculator = new OvalParametersCalculator();
    private Map<Integer, OvalParameters> parametersMap = new HashMap<Integer, OvalParameters>();

    private OvalObserver(){}

    public static OvalObserver getOBSERVER() {
        return OBSERVER;
    }

    public Map<Integer, OvalParameters> getParametersMap() {
        return parametersMap;
    }

    public void update(Oval oval) {
        double perimeter = calculator.calculatePerimeter(oval);
        double area = calculator.calculateArea(oval);
        OvalParameters parameters = new OvalParameters(perimeter, area);
        parametersMap.put(oval.getID(), parameters);
    }

}
