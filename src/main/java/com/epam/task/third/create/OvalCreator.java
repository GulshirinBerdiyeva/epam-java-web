package com.epam.task.third.create;

import com.epam.task.third.data.DataException;
import com.epam.task.third.data.TxtFileReader;
import com.epam.task.third.entity.Oval;
import com.epam.task.third.entity.Point;
import com.epam.task.third.tool.Parser;
import com.epam.task.third.tool.Validator;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OvalCreator {
    private final static Logger LOGGER = Logger.getLogger(OvalCreator.class);
    private TxtFileReader reader = new TxtFileReader();
    private Validator validator = new Validator();
    private Parser parser = new Parser();

    public OvalCreator(TxtFileReader reader, Validator validator, Parser parser) {
        this.reader = reader;
        this.validator = validator;
        this.parser = parser;
    }

    public List<Oval> create(String fileName) throws DataException, IOException {
        List<Oval> ovals = new ArrayList<Oval>();
        List<String> lines = reader.read(fileName);
        LOGGER.info("Data read successfully");

        for (String line : lines){
            boolean valid = validator.isValid(line);
            if (valid){
                List<Double> points = parser.parse(line);
                Point point1 = new Point(points.get(0), points.get(1));
                Point point2 = new Point(points.get(2), points.get(3));

                Oval oval = new Oval(point1, point2);
                ovals.add(oval);
            }
        }
        return ovals;
    }
}
