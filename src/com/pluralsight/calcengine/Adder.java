package com.pluralsight.calcengine;

public class Adder extends CalculateBase {

    @Override
    public void calculate () {
        //setResult( getLeftVal() + getRightVal() );

        double value = getLeftVal() + getRightVal();
        setResult(value);
    }
}
