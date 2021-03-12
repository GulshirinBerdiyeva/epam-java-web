package com.epam.task.third.observe;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.logic.OvalParametersCalculator;
import com.epam.task.third.parameters.OvalParameters;
import org.apache.log4j.Logger;

import java.util.*;

public class OvalObserver implements Observer {
    private final static Logger LOGGER = Logger.getLogger(OvalObserver.class);
    private final static OvalObserver INSTANCE = new OvalObserver();
    private OvalParametersCalculator calculator = new OvalParametersCalculator();
    private Map<Integer, OvalParameters> parametersMap = new HashMap<Integer, OvalParameters>();

    private OvalObserver(){}

    public static OvalObserver getInstance() {
        return INSTANCE;
    }

    public OvalParameters getParametersMap(int id) {
        if (parametersMap.containsKey(id)){
            return parametersMap.get(id);
        }
        LOGGER.info("Element with id = " + id + "is missing");
        return null;
    }

    public void update(Oval oval) {
        double perimeter = calculator.calculatePerimeter(oval);
        double area = calculator.calculateArea(oval);
        OvalParameters parameters = new OvalParameters(perimeter, area);
        parametersMap.put(oval.getId(), parameters);
    }

}