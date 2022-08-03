package com.pluralsight.calcengine;

public class MathEquation {
    //state of MathEquation class
    private double leftVal;
    private double rightVal;
    private char opCode;
    private double result;

    //get back the average result from a series of calculations across all instances
    private static int numOfCalculations;
    private static double sumTotal;
    private static double avgTotal;


    public MathEquation() {}  //default Constructor, created automatically for us

    public MathEquation(char opCode) {
        this.opCode = opCode;
    }

    public MathEquation(char opCode, double leftVal, double rightVal) {
        //this.opCode = opCode;  instead of this lets Chain Constructors
        this(opCode);
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }

    void execute() {
        double result = 0.0;
        switch (opCode) {
            case 'a' : this.result = leftVal + rightVal; break;
            case 's' : this.result = leftVal - rightVal; break;
            case 'm' : this.result = leftVal * rightVal; break;
            case 'd' : this.result = rightVal != 0 ? leftVal / rightVal : 0.0d; break;
            default : System.out.println("Invalid letter: " + opCode);
        }
        numOfCalculations++;
        sumTotal += this.result;
        //avgTotal =sumTotal/numOfCalculations;
    }


    // SETTERS
    public void setLeftVal(double leftVal) {
        this.leftVal = leftVal;
    }

    public void setRightVal(double rightVal) {
        this.rightVal = rightVal;
    }

    public void setOpCode(char opCode) {
        this.opCode = opCode;
    }

    // GETTERS
    public double getResult() {
        return result;
    }

    public static double getAvgTotal() {
        return sumTotal/numOfCalculations;
    }
}
