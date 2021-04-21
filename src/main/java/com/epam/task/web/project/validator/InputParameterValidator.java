package com.epam.task.web.project.validator;

public class InputParameterValidator implements Validator{

    @Override
    public boolean isValid(String inputParameter) {
        return inputParameter != null && !inputParameter.trim().isEmpty();
    }

}
