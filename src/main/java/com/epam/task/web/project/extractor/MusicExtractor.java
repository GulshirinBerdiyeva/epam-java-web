package com.epam.task.web.project.extractor;

import org.apache.commons.fileupload.FileItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicExtractor implements Extractor{

    @Override
    public Object extract(Object content) throws ExtractException {
        List<FileItem> musicData = (List<FileItem>) content;
        Map<String, String> inputParameters = new HashMap<>();

        for (FileItem item : musicData) {
            String fieldName = item.getFieldName();
            String fieldValue;

            if (item.isFormField()) {
                fieldValue = item.getString();
            } else {
                fieldValue = item.getName();
            }

            inputParameters.put(fieldName, fieldValue);
        }

        return inputParameters;
    }

}
