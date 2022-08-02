package com.pluralsight.calcengine;

public class MathEquation {
    //state of MathEquation class
    double leftVal;
    double rightVal;
    char opCode;
    double result;
    int counter;

    MathEquation() {
        counter = 0;

    }

    void execute() {
        counter++;
        double result = 0.0;
        switch (opCode) {
            case 'a' -> this.result = leftVal + rightVal;
            case 's' -> this.result = leftVal - rightVal;
            case 'm' -> this.result = leftVal * rightVal;
            case 'd' -> this.result = rightVal != 0 ? leftVal / rightVal : 0.0d;
            default -> System.out.println("Invalid letter: " + opCode);
        }
    }

}
