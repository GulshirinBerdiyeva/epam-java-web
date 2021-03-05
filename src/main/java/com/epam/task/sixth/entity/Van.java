package com.epam.task.sixth.entity;

import com.epam.task.sixth.logic.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Van implements Runnable{
    private final static Logger LOGGER = LogManager.getLogger(Van.class);
    private final static Base BASE = Base.getInstance();
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
        if (!this.getIsUrgent()){
            try {
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        BASE.process(this);
        LOGGER.info(this.toString());
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