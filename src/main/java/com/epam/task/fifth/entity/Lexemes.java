package com.epam.task.fifth.entity;

public class Lexemes implements Component{
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int countElements() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lexemes)) {
            return false;
        }

        Lexemes lexemes = (Lexemes) o;

        return type != null ? type.equals(lexemes.type) : lexemes.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

}