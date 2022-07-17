package com.epam.task.fifth.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpressionCalculator {
    private List<Interpreter> listExpression = new ArrayList<>();
    private final static String REGEX = "\\p{Blank}+";

    public Number calculate(String expression) {
        parse(expression);
        ArrayDeque<Double> stack = new ArrayDeque<>();

        for (Interpreter element : listExpression) {
            element.interpret(stack);
        }

        return stack.pop();
    }

    private void parse(String expression) {
        String[] lexemes = expression.split(REGEX);

        for (String lexeme : lexemes) {
            if (lexeme.isEmpty()) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch (temp) {
                case '+':
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case '-':
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case '*':
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                case '/':
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                default:
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextDouble()) {
                        listExpression.add(
                                new NonTerminalExpressionNumber(scan.nextDouble()));
                    }
            }
        }

    }

}