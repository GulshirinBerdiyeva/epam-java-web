package com.epam.task.first.data;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtFileReader implements IFileReader {

    public List<String> read(String filename) throws FileException {
        FileReader reader = null;
        try {
            reader = new FileReader(filename);
            List<String> data = new ArrayList<String>();
            StringBuffer buffer = new StringBuffer();

            while(reader.ready()){
                char symbol = (char) reader.read();
                buffer.append(symbol);
            }

            data.add(buffer.toString());
            return data;
        } catch (IOException e) {
            throw new FileException("ERROR: File not found!", e);
        } finally {
            close(reader);
        }
    }

    private void close(FileReader reader) throws FileException {
        if (reader != null){
            try {
                reader.close();
            } catch (IOException e) {
                throw new FileException("ERROR: File not found!", e);
            }
        }
    }

}
