package com.pluralsight.calcengine;

public class CalculateBase {

    private double leftVal;
    private double rightVal;
    private double result;

    //with fields set to private, we must now use getters/setters to modify state.

    public double getLeftVal() {
        return leftVal;
    }
    public void setLeftVal(double leftVal) {
        this.leftVal = leftVal;
    }

    public double getRightVal() {
        return rightVal;
    }
    public void setRightVal(double rightVal) {
        this.rightVal = rightVal;
    }

    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }

    //not going to calculate in this class, derived classes will do the calculations (adder,subtracter, etc..)
    public void calculate() {}

}
