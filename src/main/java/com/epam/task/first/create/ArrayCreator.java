package com.epam.task.first.create;

import com.epam.task.first.tool.ArrayParser;
import com.epam.task.first.tool.ArrayValidator;
import com.epam.task.first.data.FileException;
import com.epam.task.first.data.TxtFileReader;
import com.epam.task.first.entity.Array;

import java.util.ArrayList;
import java.util.List;

public class ArrayCreator {

    private TxtFileReader reader;
    private ArrayValidator validator;
    private ArrayParser parser;

    public ArrayCreator(TxtFileReader reader,
                        ArrayValidator validator,
                        ArrayParser parser) {
        this.reader = reader;
        this.validator = validator;
        this.parser = parser;
    }

    public List<Array> create(String filename) throws FileException {
        List<Array> array = new ArrayList<Array>();
        List<String> stringArray = reader.read(filename);

        for (int i = 0; i < stringArray.size(); i++){
            String line = stringArray.get(i);
            boolean valid = validator.isValid(line);
            if (valid){
                Array validArray = parser.parse(line);
                array.add(validArray);
            }
        }
        return array;
    }

}
