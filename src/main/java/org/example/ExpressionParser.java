package org.example;

import java.util.Stack;

public class ExpressionParser {
    private static final double PI = 3.1415926535897;

    protected static double parser(StringBuilder expression) {
        char[] tokens = expression.toString().toCharArray();
        Stack<Double> valueStack = new Stack<Double>();
        Stack<Character> operatorStack = new Stack<Character>();
        double numBPoint = 0;
        double numAPoint = 0;

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;
            if (tokens[i] == 'π') {
                valueStack.push(PI);
            } else {
                if (tokens[i] >= '0' && tokens[i] <= '9') {
                    //Nếu kí tự là số, thêm vào Value Stack
                    StringBuilder tempStr = new StringBuilder();
                    while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                        //Xử lí trường hợp số có nhiều chữ số
                        tempStr.append(tokens[i++]);
                    }
                    numBPoint = Double.parseDouble(tempStr.toString());
                    valueStack.push(numBPoint);
                    i--;
                } else {
                    if (tokens[i] == '.') {//Xử lí trường hợp số thập phân
                        StringBuilder tempStr = new StringBuilder();
                        int n = i + 1;
                        while (n < tokens.length && tokens[n] >= '0' && tokens[n] <= '9') {
                            //Xử lí trường hợp có nhiều chữ số sau dấu phẩy
                            tempStr.append(tokens[n++]);
                        }
                        n--;
                        i = n;
                        numAPoint = Double.parseDouble(tempStr.toString());
                        numAPoint = numAPoint / (Math.pow(10, (tempStr.toString().length())));
                        valueStack.pop();
                        valueStack.push(numBPoint + numAPoint);
                    } else {
                        if (tokens[i] == '(') {
                            operatorStack.push(tokens[i]);
                        } else {
                            if (tokens[i] == ')') {
                                while (operatorStack.peek() != '(') {
                                    double topElement = valueStack.pop();
                                    valueStack.push(Execution.calculate(operatorStack.pop(), valueStack.pop(), topElement));
                                }
                                operatorStack.pop();
                            } else {
                                if (Execution.isOperator(tokens[i])) {
                                    while (!operatorStack.empty() && Execution.hasPrecedence(tokens[i], operatorStack.peek())) {
                                        double topElement = valueStack.pop();
                                        valueStack.push(Execution.calculate(operatorStack.pop(), valueStack.pop(), topElement));
                                    }
                                    operatorStack.push(tokens[i]);
                                    if (tokens[i] == '√') {
                                        valueStack.push((double) 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        while (!operatorStack.empty()) {
            double topElement = valueStack.pop();
            valueStack.push(Execution.calculate(operatorStack.pop(), valueStack.pop(), topElement));
        }
        return valueStack.pop();
    }

}

