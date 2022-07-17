package com.epam.task.sixth.main;

import com.epam.task.sixth.entity.Van;
import com.epam.task.sixth.entity.Vans;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Main {
    private final static String FILE_NAME = "src/main/resources/input.json";

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vans data = objectMapper.readValue(new File(FILE_NAME), Vans.class);
        List<Van> vans = data.getVans();

        vans.stream().forEach(System.out::println);

        ExecutorService executorService = Executors.newFixedThreadPool(vans.size());
        List<Future<?>> futures = vans.stream()
                                        .map(van -> executorService.submit(van))
                                        .collect(Collectors.toList());
        executorService.shutdown();

        for (Future<?> future : futures){
            future.get();
        }

    }

}