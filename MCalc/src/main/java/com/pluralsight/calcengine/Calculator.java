package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Calculator {


    private char opCode;
    private double leftVal;
    private double rightVal;
    private double result = 0;

    private static int allInstances;

    Calculator() {}
    Calculator(char opCode ) {
        this.opCode = opCode;
    }

    Calculator(char opCode, double leftVal, double rightVal){
        this(opCode);
        this.leftVal = leftVal;
        this.rightVal = rightVal;

    }

    public double calcResult( ) {
        switch (this.opCode) {
            case '+' : this.result = leftVal + rightVal; break;
            case '-' : this.result = leftVal - rightVal; break;
            case '*' : this.result = leftVal * rightVal; break;
            case '/' : this.result = rightVal != 0 ? leftVal / rightVal : 0; break;
            default :
                log.info("Invalid letter: {}", this.opCode);
        }
        return result;

    }

    public void printResult () {
        log.info(String.valueOf(this.result));
    }

    public static void setAllInstances() {
        allInstances++;
    }
    public void getAllInstances() {
        log.info(String.valueOf(allInstances));
    }

}
