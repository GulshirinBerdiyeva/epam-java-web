package com.epam.task.third.observe;

import com.epam.task.third.entity.Oval;
import com.epam.task.third.logic.OvalParametersCalculator;
import com.epam.task.third.parameters.OvalParameters;
import org.apache.log4j.Logger;

import java.util.*;

public class OvalObserver implements IObserver {
    private final static OvalObserver OBSERVER = new OvalObserver();
    private OvalParametersCalculator calculator = new OvalParametersCalculator();
    private Map<Integer, OvalParameters> parametersMap = new HashMap<Integer, OvalParameters>();
    private final static Logger LOGGER = Logger.getLogger(OvalObserver.class);

    private OvalObserver(){}

    public static OvalObserver getOBSERVER() {
        return OBSERVER;
    }

    public OvalParameters getParametersMap(Integer ID) {
        Set<Map.Entry<Integer, OvalParameters>> set = parametersMap.entrySet();
        Iterator<Map.Entry<Integer, OvalParameters>> elements = set.iterator();

        while (elements.hasNext()){
            Map.Entry<Integer, OvalParameters> element = elements.next();
            if (element.getKey() == ID){
                return element.getValue();
            }
        }
        LOGGER.info("Element with ID = " + ID + "is missing");
        return null;
    }

    public void update(Oval oval) {
        double perimeter = calculator.calculatePerimeter(oval);
        double area = calculator.calculateArea(oval);
        OvalParameters parameters = new OvalParameters(perimeter, area);
        parametersMap.put(oval.getID(), parameters);
    }

}
