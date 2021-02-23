package com.epam.task.fifth.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{
    private String component;
    private List<Component> text = new ArrayList<>();

    public Composite() {
    }

    public Composite(String component) {
        this.component = component;
    }

    @Override
    public int countElements() {
        int count = 0;
        for (Component component : text){
            count += 1;
        }
        return count;
    }

    public void add(Component component){
        text.add(component);
    }

    public void remove(Component component){
        text.remove(component);
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

        if (component != null ? !component.equals(composite.component) : composite.component != null) {
            return false;
        }

        return text != null ? text.equals(composite.text) : composite.text == null;
    }

    @Override
    public int hashCode() {
        int result = component != null ? component.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return !text.isEmpty() ?
                "Composite{ text = '" + text + "' }" :
                "Composite{ component = '" + component + "' }";
    }

}