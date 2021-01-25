package com.epam.task.first.create;

import com.epam.task.first.check.ArrayParser;
import com.epam.task.first.check.ArrayValidator;
import com.epam.task.first.data.TxtFileReader;
import com.epam.task.first.entities.Array;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public List<Array> process(String filename){
        //read lines from file
        //validate each line
        //for each valid line create array
        //return result
        throw new NotImplementedException();
    }


}
