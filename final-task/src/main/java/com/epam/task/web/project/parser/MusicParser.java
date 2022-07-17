package com.epam.task.web.project.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MusicParser implements Parser<FileItem> {

    private static final String UTF_8 = "UTF-8";

    private static final int SEMAPHORE_PERMITS = 10;
    private final Semaphore semaphore = new Semaphore(SEMAPHORE_PERMITS);
    private final Lock lock = new ReentrantLock();

    @Override
    public Map<?, ?> parse(List<FileItem> values) throws ParserException {
        try {
            semaphore.acquire();
            lock.lock();

            Map<String, String> inputParameters = new HashMap<>();

            for (FileItem item : values) {
                String fieldName = item.getFieldName();

                String fieldValue = item.isFormField() ? item.getString(UTF_8) : item.getName();

                inputParameters.put(fieldName, fieldValue);
            }

            return inputParameters;

        } catch (InterruptedException | UnsupportedEncodingException e) {
            throw new ParserException(e.getMessage(), e);

        } finally {
            semaphore.release();
            lock.unlock();
        }

    }

}
