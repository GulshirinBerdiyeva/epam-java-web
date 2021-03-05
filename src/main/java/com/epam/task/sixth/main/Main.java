package com.epam.task.sixth.main;

import com.epam.task.sixth.entity.Van;
import com.epam.task.sixth.entity.Vans;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private final static String FILE_NAME = "src/main/resources/input.json";

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vans holder=objectMapper.readValue(new File(FILE_NAME), Vans.class);
        List<Van> vehicles = holder.getVans();

        vehicles.stream().forEach(System.out::println);

    }
}
