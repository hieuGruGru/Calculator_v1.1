package org.example;

public class Execution {
    protected static double calculate (char op, double a, double b) {
        switch (op){
            case '+':
                return Addition(a, b);
            case '-':
                return Subtraction(a, b);
            case '*':
                return Multiplication(a, b);
            case '/':
            case ':':
                if (b == 0) throw new UnsupportedOperationException("");
                return Division(a, b);
            case '^':
                return Exponential(a, b);
            case '√':
                return SquareRoot(a, b);
            case '%':
                return Remainder(a, b);
            case '!':
                return Factorial(a,b);
        }
        return 0;
    }
    private static double Addition (double a, double b) {
        return a + b;
    }

    private static double Subtraction (double a, double b) {
        return a - b;
    }

    private static double Multiplication (double a, double b) {
        return a * b;
    }

    private static double Division (double a, double b) {
        return a / b;
    }

    private static double Exponential (double a, double b) {
        return Math.pow(a, b);
    }

    private static double SquareRoot (double a, double b) {
        return Math.pow(b, 0.5);
    }

    private static double Remainder (double a, double b) {
        return a % b;
    }

    private static double Factorial (double a, double b) {
        a = Math.round(a);
        if (a == 0 || a == 1) {
            return 1;
        } else {
            return a*Factorial(a - 1,1);
        }
    }

    protected static boolean isOperator (char a) {
        if
        (a == '+' || a == '-'  ||
         a == '*' || a == '.'  ||
         a == '/' || a == ':'  ||
         a == '^' || a == '%'  ||
         a == '!' || a == '√'  ){
            return true;
        }
        else {
            throw new UnsupportedOperationException("Khoan đã ... Nhập cái toán tử gì vậy ba");
        }
    }

    protected static boolean hasPrecedence (char op1, char op2) {//trả về true nếu op2 ưu tiên hơn op1
        if (op2 == '(' || op2 == ')')
            return false;
        if (op1 == '^' || op1 == '√')
            return false;
        if ((op1 == '*' || op1 == '/' || op1 == ':') && (op2 == '+' || op2 == '-' || op2 == '%' || op2 == '!'))
            return false;
        else
            return true;
    }

}