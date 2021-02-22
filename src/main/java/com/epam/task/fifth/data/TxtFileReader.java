package com.epam.task.fifth.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtFileReader {
    private Logger LOGGER = LogManager.getLogger(TxtFileReader.class);

    public String read(String fileName) throws DataException, IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String text = "";

            while (reader.ready()){
                text += reader.readLine();
            }

            LOGGER.info("Data read successfully");
            return text;
        } catch (IOException e) {
            throw new DataException(e.getMessage(), e);
        } finally {
            if (reader != null){
                reader.close();
            }
        }
    }

}