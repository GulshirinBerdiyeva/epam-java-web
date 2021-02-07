package com.epam.task.third.create;

import com.epam.task.third.data.DataException;
import com.epam.task.third.data.TxtFileReader;
import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import com.epam.task.third.tool.Parser;
import com.epam.task.third.tool.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OvalCreator {
    private TxtFileReader reader;
    private Validator validator;
    private Parser parser;

    public OvalCreator(TxtFileReader reader, Validator validator, Parser parser) {
        this.reader = reader;
        this.validator = validator;
        this.parser = parser;
    }

    public List<Oval> create(String fileName) throws DataException, IOException {
        List<Oval> ovals = new ArrayList<Oval>();
        List<String> lines = reader.read(fileName);

        for (String line : lines){
            boolean valid = validator.isValid(line);
            if (valid){
                List<Double> points = parser.parse(line);
                Point point1 = new Point(points.get(0), points.get(1));
                Point point2 = new Point(points.get(2), points.get(3));

                int id = (int) (Math.random() * 42);
                Oval oval = new Oval(id, point1, point2);
                ovals.add(oval);
            }
        }
        return ovals;
    }

}
