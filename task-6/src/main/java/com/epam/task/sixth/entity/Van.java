package com.epam.task.sixth.entity;

import com.epam.task.sixth.logic.Base;

public class Van implements Runnable{
    private int id;
    private boolean isUrgent;
    private boolean isLoaded;

    public Van() {}

    public Van(int id, boolean isUrgent, boolean isLoaded) {
        this.id = id;
        this.isUrgent = isUrgent;
        this.isLoaded = isLoaded;
    }

    public int getId() {
        return id;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public boolean getIsLoaded() {
        return isLoaded;
    }

    @Override
    public void run() {
        Base base = Base.getInstance();
        base.process(this);
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