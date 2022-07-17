package com.epam.task.fifth.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{
    private List<Component> components = new ArrayList<>();

    public Composite() {
    }

    public Composite(List<Component> components) {
        this.components = components;
    }

    public void add(Component component){
        components.add(component);
    }

    public void remove(Component component){
        components.remove(component);
    }

    public int componentsCount() {
        int count = 0;
        for (Component component : components){
            count += 1;
        }
        return count;
    }

    public Component getChild(int index) {
        return index <= this.componentsCount() ? components.get(index) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Composite)) {
            return false;
        }

        Composite composite = (Composite) o;

        return components != null ? components.equals(composite.components) : composite.components == null;
    }

    @Override
    public int hashCode() {
        return components != null ? components.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.valueOf(components);
    }

}