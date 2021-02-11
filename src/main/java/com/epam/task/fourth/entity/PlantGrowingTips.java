package com.epam.task.fourth.entity;

public class PlantGrowingTips {
    private int temperature;
    private int watering;
    private ELighting lighting;

    public PlantGrowingTips(int temperature, int watering, ELighting lighting) {
        this.temperature = temperature;
        this.watering = watering;
        this.lighting = lighting;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public ELighting getLighting() {
        return lighting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlantGrowingTips)) return false;

        PlantGrowingTips that = (PlantGrowingTips) o;

        if (temperature != that.temperature) return false;
        if (watering != that.watering) return false;
        return lighting == that.lighting;
    }

    @Override
    public int hashCode() {
        int result = temperature;
        result = 31 * result + watering;
        result = 31 * result + (lighting != null ? lighting.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlantGrowingTips{" +
                "temperature=" + temperature +
                ", watering=" + watering +
                ", lighting=" + lighting +
                '}';
    }

}
