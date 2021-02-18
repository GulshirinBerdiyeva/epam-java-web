package com.epam.task.fourth.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://www.example.com/orangery")
public class Orangery {
    @XmlAnyElement
    private List<Plant> plants = new ArrayList<>();

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    @Override
    public String toString() {
        return "Orangery{" +
                "plants=" + plants +
                '}';
    }

}