package com.epam.task.first.data;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader implements IFileReader {

    public List<String> read(String filename) throws FileException {
        try {
            FileReader reader = new FileReader(filename);
            List<String> data = new ArrayList<String>();
            StringBuffer buffer = new StringBuffer();

            while(reader.ready()){
                char symbol = (char) reader.read();
                buffer.append(symbol);
            }
            data.add(buffer.toString());
            reader.close();
            return data;
        } catch (IOException e) {
            throw new FileException("ERROR: File not found!", e.getCause());
        }
    }

}
