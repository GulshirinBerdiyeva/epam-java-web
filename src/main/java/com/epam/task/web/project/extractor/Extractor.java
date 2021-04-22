package com.epam.task.web.project.extractor;

public interface Extractor {
    Object extract(Object content) throws ExtractException;
}
