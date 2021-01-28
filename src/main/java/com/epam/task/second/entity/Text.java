package com.epam.task.second.entity;

public class Text {
    private CharSequence text;

    public Text(String text) {
        this.text = text;
    }

    public Text(StringBuilder text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
