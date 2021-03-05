package com.epam.task.sixth.entity;

public class Van implements Runnable{
    private int id;
    private boolean isLoaded;
    private boolean isUrgent;

    public Van() {}

    public Van(int id, boolean isLoaded, boolean isUrgent) {
        this.id = id;
        this.isLoaded = isLoaded;
        this.isUrgent = isUrgent;
    }

    public int getId() {
        return id;
    }

    public boolean getIsLoaded() {
        return isLoaded;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    @Override
    public void run() {

    }

    @Override
    public String toString() {
        return "Van{" +
                "id=" + id +
                ", isLoaded=" + isLoaded +
                ", isUrgent=" + isUrgent +
                '}';
    }

}