package com.epam.task.web.project.validator;

/**
 * Implements by definite Validator classes
 * */
public interface Validator {
    /**
     * Check parameter's value
     *
     * @param inputValue parameter's value
     * @return boolean
     * */
    boolean isValid(String inputValue);

}
