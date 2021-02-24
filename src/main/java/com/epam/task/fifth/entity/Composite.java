package com.epam.task.fifth.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{
    private List<Component> text = new ArrayList<>();

    public Composite() {
    }

    public Composite(List<Component> text) {
        this.text = text;
    }

    public void add(Component component){
        text.add(component);
    }

    public void remove(Component component){
        text.remove(component);
    }

    public int componentsCount() {
        int count = 0;
        for (Component component : text){
            count += 1;
        }
        return count;
    }

    public Component getChild(int index) {
        return index <= this.componentsCount() ? text.get(index) : null;
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

        return text != null ? text.equals(composite.text) : composite.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.valueOf(text);
    }

}