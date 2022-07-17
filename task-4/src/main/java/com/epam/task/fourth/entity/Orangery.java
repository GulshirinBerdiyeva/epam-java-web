
package com.epam.task.fourth.entity;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "http://www.example.com/orangery")
public class Orangery {
    @XmlElements({
            @XmlElement(name = "plant", type = Plant.class, namespace = "http://www.example.com/orangery"),
            @XmlElement(name = "rosacea", type = Rosacea.class, namespace = "http://www.example.com/orangery")
    })
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