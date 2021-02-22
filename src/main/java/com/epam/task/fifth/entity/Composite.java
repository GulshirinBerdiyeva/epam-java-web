package com.epam.task.fifth.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component{
    private List<Component> text = new ArrayList<>();

    @Override
    public int countElements() {
        int count = 0;
        for (Component component : text){
            count += component.countElements();
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

        return text != null ? text.equals(composite.text) : composite.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Composite{" +
                "text=" + text +
                '}';
    }

}