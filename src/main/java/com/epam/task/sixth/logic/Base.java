package com.epam.task.sixth.logic;

import com.epam.task.sixth.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Base {
    private final static Logger LOGGER = LogManager.getLogger(Base.class);
    private final static AtomicReference<Base> INSTANCE = new AtomicReference<>();
    private final static AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private final static int TERMINALS_NUMBER = 5;
    private final static Semaphore SEMAPHORE = new Semaphore(TERMINALS_NUMBER);
    private static Lock lock = new ReentrantLock();

    private Base(){}

    public static Base getInstance(){
        if (!IS_INSTANCE_CREATED.get()){
            lock.lock();
            try{
                if (!IS_INSTANCE_CREATED.get()){
                    INSTANCE.set(new Base());
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created Base instance");
                }

            } finally {
                lock.unlock();
            }
        }

        return INSTANCE.get();
    }

    public void process(Van van){
        try {
            SEMAPHORE.acquire();
            boolean isLoaded = van.getIsLoaded();
            van.setLoaded(!isLoaded);

        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);

        } finally {
            SEMAPHORE.release();
        }
    }

}