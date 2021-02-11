package com.epam.task.fourth.entity;

public class Dicot extends AbstractPlant{
    private final static int COTYLEDONS = 2;

    public Dicot(int ID, String name,
                 PlantVisualParameters visualParameters,
                 PlantGrowingTips growingTips) {
        super(ID, name, visualParameters, growingTips);
    }

    public static int getCOTYLEDONS() {
        return COTYLEDONS;
    }

}
