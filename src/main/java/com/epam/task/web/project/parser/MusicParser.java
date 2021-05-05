package com.epam.task.web.project.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicParser {

    private static final String UTF_8 = "UTF-8";

    public Map<String, String> extractInputParameters(List<FileItem> musicData) throws ParserException {
        Map<String, String> inputParameters = new HashMap<>();
        try {
            for (FileItem item : musicData) {
                String fieldName = item.getFieldName();
                String fieldValue;

                if (item.isFormField()) {
                    fieldValue = item.getString(UTF_8);
                } else {
                    fieldValue = item.getName();
                }

                inputParameters.put(fieldName, fieldValue);
            }
        } catch (UnsupportedEncodingException e) {
            throw new ParserException(e.getMessage(), e);
        }

        return inputParameters;
    }

}
