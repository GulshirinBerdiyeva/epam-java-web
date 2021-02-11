package com.epam.task.fourth.entity;

public class Monocot extends AbstractPlant{
    private final static int COTYLEDONS = 1;

    public Monocot(int ID, String name,
                   PlantVisualParameters visualParameters,
                   PlantGrowingTips growingTips) {
        super(ID, name, visualParameters, growingTips);
    }

    public static int getCOTYLEDONS() {
        return COTYLEDONS;
    }

}
