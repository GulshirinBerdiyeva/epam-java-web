package com.epam.task.web.project.validator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class AbstractValidator implements Validator {

    protected boolean isNullOrEmpty(String inputValue) {
        return inputValue == null || inputValue.trim().isEmpty();
    }

    @Override
    public boolean isValid(String inputValue) {
        throw new NotImplementedException();
    }

}
