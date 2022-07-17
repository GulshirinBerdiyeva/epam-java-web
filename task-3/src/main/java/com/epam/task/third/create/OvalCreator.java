package com.epam.task.third.create;

import com.epam.task.third.data.DataException;
import com.epam.task.third.data.DataReader;
import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import com.epam.task.third.logic.IdGenerator;
import com.epam.task.third.tool.DoubleParser;
import com.epam.task.third.tool.LineValidator;

import java.util.ArrayList;
import java.util.List;

public class OvalCreator {
    private DataReader reader;
    private LineValidator validator;
    private DoubleParser parser;
    private IdGenerator generator;

    public OvalCreator(DataReader reader, LineValidator validator, DoubleParser parser, IdGenerator generator) {
        this.reader = reader;
        this.validator = validator;
        this.parser = parser;
        this.generator = generator;
    }

    public List<Oval> create(String fileName) throws DataException {
        List<Oval> ovals = new ArrayList<Oval>();
        List<String> lines = reader.read(fileName);

        for (String line : lines){
            boolean valid = validator.isValid(line);
            if (valid){
                List<Double> points = parser.parse(line);
                Point firstPoint = new Point(points.get(0), points.get(1));
                Point secondPoint = new Point(points.get(2), points.get(3));

                int id = generator.generateId();
                Oval oval = new Oval(id, firstPoint, secondPoint);
                ovals.add(oval);
            }
        }
        return ovals;
    }

}