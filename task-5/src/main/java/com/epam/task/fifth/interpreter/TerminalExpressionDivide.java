package com.epam.task.fifth.interpreter;

import java.util.ArrayDeque;

public class TerminalExpressionDivide implements Interpreter{
    @Override
    public void interpret(ArrayDeque<Double> stack) {
        stack.push(stack.pop() / stack.pop());
    }
}