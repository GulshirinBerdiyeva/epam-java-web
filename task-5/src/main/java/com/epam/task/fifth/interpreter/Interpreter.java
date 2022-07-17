package com.epam.task.fifth.interpreter;

import java.util.ArrayDeque;

public interface Interpreter {
    void interpret(ArrayDeque<Double> stack);
}