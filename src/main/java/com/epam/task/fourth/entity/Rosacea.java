package com.epam.task.fourth.entity;

public class Rosacea extends Dicot{
    private boolean thorns;

    public Rosacea() {}

    public Rosacea(String ID, String name,
                   PlantVisualParameters visualParameters,
                   PlantGrowingTips growingTips, boolean thorns) {
        super(ID, name, visualParameters, growingTips);
        this.thorns = thorns;
    }

    public boolean isThorns() {
        return thorns;
    }

    public void setThorns(boolean thorns) {
        this.thorns = thorns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rosacea)) return false;
        if (!super.equals(o)) return false;

        Rosacea rosacea = (Rosacea) o;

        return thorns == rosacea.thorns;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (thorns ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rosacea{" +
                "thorns=" + thorns +
                '}';
    }

}
