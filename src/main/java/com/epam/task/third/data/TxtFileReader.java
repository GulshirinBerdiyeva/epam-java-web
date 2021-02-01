package com.epam.task.third.data;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader {
    private final static Logger LOGGER = Logger.getLogger(TxtFileReader.class);

    public List<String> read(String fileName) throws DataException{
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String> data = new ArrayList<String>();
            while (reader.ready()){
                data.add(reader.readLine());
            }
            LOGGER.info("Data read successfully");
            return data;
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage(), e);
            throw new DataException(e.getMessage(), e);
        }
    }

}
