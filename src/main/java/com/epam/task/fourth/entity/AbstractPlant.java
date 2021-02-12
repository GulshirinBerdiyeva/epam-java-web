package com.epam.task.fourth.entity;

public abstract class AbstractPlant {
    private String ID;
    private String name;
    private PlantVisualParameters visualParameters;
    private PlantGrowingTips growingTips;

    public AbstractPlant() {}

    public AbstractPlant(String ID, String name,
                         PlantVisualParameters visualParameters,
                         PlantGrowingTips growingTips) {
        this.ID = ID;
        this.name = name;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantVisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(PlantVisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public PlantGrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(PlantGrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlant)) return false;

        AbstractPlant that = (AbstractPlant) o;

        if (ID != null ? !ID.equals(that.ID) : that.ID != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (visualParameters != null ? !visualParameters.equals(that.visualParameters) : that.visualParameters != null)
            return false;
        return growingTips != null ? growingTips.equals(that.growingTips) : that.growingTips == null;
    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        result = 31 * result + (growingTips != null ? growingTips.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractPlant{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", visualParameters=" + visualParameters +
                ", growingTips=" + growingTips +
                '}';
    }

}
