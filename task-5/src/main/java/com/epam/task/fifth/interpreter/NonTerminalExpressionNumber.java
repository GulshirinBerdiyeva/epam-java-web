package com.epam.task.fifth.interpreter;

import java.util.ArrayDeque;

public class NonTerminalExpressionNumber implements Interpreter{
    private double number;

    public NonTerminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(ArrayDeque<Double> stack) {
        stack.push(number);
    }

}