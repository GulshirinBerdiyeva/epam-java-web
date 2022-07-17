package com.epam.task.sixth.logic;

import com.epam.task.sixth.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terminal {
    private final static Logger LOGGER = LogManager.getLogger(Terminal.class);

    public void process(Van van){
        boolean isLoaded = van.getIsLoaded();
        van.setLoaded(!isLoaded);

        LOGGER.info(van + " processed");
    }

}