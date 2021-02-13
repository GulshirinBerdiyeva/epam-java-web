package com.epam.task.second.entity;

public class Text {
   private String text;

    public Text(String text) {
        this.text = text;
    }

    public Text(CharSequence text) {
        this.text = text.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Text)) {
            return false;
        }

        Text text1 = (Text) o;

        return text != null ? text.equals(text1.text) : text1.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public String toString() {
        return text;
    }

}
