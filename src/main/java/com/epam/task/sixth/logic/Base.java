package com.epam.task.sixth.logic;

import com.epam.task.sixth.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Base {
    private final static Logger LOGGER = LogManager.getLogger(Base.class);

    private final static AtomicReference<Base> INSTANCE = new AtomicReference<>();
    private static AtomicBoolean isInstanceCreated = new AtomicBoolean();
    private final static Lock INSTANCE_LOCK = new ReentrantLock();

    private final static int TERMINALS_NUMBER = 5;
    private final static Semaphore SEMAPHORE = new Semaphore(TERMINALS_NUMBER);
    private List<Terminal> terminals;
    private final Lock terminalLock = new ReentrantLock();

    private Queue<Van> vans;
    private final Lock vanLock = new ReentrantLock();


    private Base(){
        this.terminals = IntStream.range(0, TERMINALS_NUMBER)
                                    .mapToObj(terminal -> new Terminal())
                                    .collect(Collectors.toList());

        this.vans = new PriorityQueue<>((first, second) ->
                                        Boolean.compare(second.getIsUrgent(), first.getIsUrgent()));
    }

    public static Base getInstance(){
        if (!isInstanceCreated.get()){
            INSTANCE_LOCK.lock();
            try{
                if (!isInstanceCreated.get()){
                    Base base = new Base();
                    INSTANCE.set(base);
                    isInstanceCreated.set(true);

                    LOGGER.info("Created Base instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    public void process(Van van){
        try {
            SEMAPHORE.acquire();

            vanLock.lock();
            vans.add(van);
            vanLock.unlock();

            terminalLock.lock();
            Terminal terminal = terminals.remove(0);

            vanLock.lock();
            Van currentVan = vans.poll();
            vanLock.unlock();

            terminal.process(currentVan);
            terminals.add(terminal);

        } catch (InterruptedException e) {
            LOGGER.fatal(e.getMessage(), e);

        } finally {
            SEMAPHORE.release();
            terminalLock.unlock();
        }
    }

}