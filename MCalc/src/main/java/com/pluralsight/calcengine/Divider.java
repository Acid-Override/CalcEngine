package com.pluralsight.calcengine;

public class Divider extends CalculateBase {

    public void calculate() {

        if (getRightVal() != 0)
          setResult( getLeftVal() / getRightVal() );
        else
            setResult(0);
    }
}
