package com.epam.task.fourth.entity;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@XmlSeeAlso(Rosacea.class)
public class Plant {
    @XmlElement
    private String name;
    @XmlElement(name = "visual-parameters")
    private PlantVisualParameters visualParameters = new PlantVisualParameters();
    @XmlAttribute(required = true)
    @XmlID
    private String id;

    public Plant() {}

    public Plant(String id, String name, PlantVisualParameters visualParameters) {
        this.id = id;
        this.name = name;
        this.visualParameters = visualParameters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plant)) {
            return false;
        }

        Plant plant = (Plant) o;

        if (id != null ? !id.equals(plant.id) : plant.id != null) {
            return false;
        }
        if (name != null ? !name.equals(plant.name) : plant.name != null) {
            return false;
        }
        return visualParameters != null ? visualParameters.equals(plant.visualParameters) : plant.visualParameters == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nid: " + id +
                "\nname: " + name +
                "\nvisual-parameters: " + visualParameters;
    }

}