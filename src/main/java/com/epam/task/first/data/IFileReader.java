package com.epam.task.first.data;

import java.util.List;
public interface IFileReader {

    List<String> read(String filename) throws DataException;

}