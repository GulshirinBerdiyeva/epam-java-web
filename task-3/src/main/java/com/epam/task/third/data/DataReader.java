package com.epam.task.third.data;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private final static Logger LOGGER = Logger.getLogger(DataReader.class);

    public List<String> read(String fileName) throws DataException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            List<String> data = new ArrayList<String>();

            while (reader.ready()){
                String line = reader.readLine();
                data.add(line);
            }
            LOGGER.info("Data read successfully");
            return data;

        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);

        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) { }
            }

        }
    }

}