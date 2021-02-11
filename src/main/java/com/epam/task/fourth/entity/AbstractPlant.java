package com.epam.task.fourth.entity;

public abstract class AbstractPlant {
    private final int ID;
    private String name;
    private PlantVisualParameters visualParameters;
    private PlantGrowingTips growingTips;

    public AbstractPlant(int ID, String name,
                         PlantVisualParameters visualParameters,
                         PlantGrowingTips growingTips) {
        this.ID = ID;
        this.name = name;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public PlantVisualParameters getVisualParameters() {
        return visualParameters;
    }

    public PlantGrowingTips getGrowingTips() {
        return growingTips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlant)) return false;

        AbstractPlant that = (AbstractPlant) o;

        if (ID != that.ID) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (visualParameters != null ? !visualParameters.equals(that.visualParameters) : that.visualParameters != null)
            return false;
        return growingTips != null ? growingTips.equals(that.growingTips) : that.growingTips == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        result = 31 * result + (growingTips != null ? growingTips.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractPlant{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", visualParameters=" + visualParameters +
                ", growingTips=" + growingTips +
                '}';
    }

}
