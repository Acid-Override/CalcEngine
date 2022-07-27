package com.pluralsight.calcengine;

public class Arrays {

    public static void main(String[] args) {
//        float[] theVals = new float[3];
//
//        theVals[0] = 10.0f;
//        theVals[1] = 20.0f;
//        theVals[2] = 15.0f;

        float[] theVals = { 10.0f, 20.0f, 15.0f };

        helperFunc(theVals);
        float result = 0.0f;

        for (float theVal : theVals) {
            System.out.println(theVal);
            result += theVal;
        }
        System.out.println("The result is: " + result);
    }

    public static void helperFunc(float[] arr) {
        for (float i : arr) {
            System.out.println(i);

        }

    }
}
