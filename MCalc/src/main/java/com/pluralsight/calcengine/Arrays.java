package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
            log.info(String.valueOf(theVal));
            result += theVal;
        }
        log.info("The result is: " + result);
    }

    public static void helperFunc(float[] arr) {
        for (float i : arr) {
            log.info(String.valueOf(i));

        }

    }
}
