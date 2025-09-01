package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtendedStaticInitializerTest extends StaticInitializerTest {

    // field values
      //static initializers

    // constructors

    // methods


    /**
     * This is a very important JavaDoc
     */
    private int someFieldValue, anotherFieldValue, someOtherFieldValue;

    //static initializer
    static {
        log.info("This is the extended static initializer being created here.");
    }


    @Override
    void someFunction() {
        log.info("Overriding someFunction method in abstract class");
    }

    @Override
    public int getSomeFieldValue() {
        return someFieldValue;
    }

    @Override
    public void setSomeFieldValue(int someFieldValue) {
        this.someFieldValue = someFieldValue;
    }

    @Override
    public int getAnotherFieldValue() {
        return anotherFieldValue;
    }

    @Override
    public void setAnotherFieldValue(int anotherFieldValue) {
        this.anotherFieldValue = anotherFieldValue;
    }

    public int getSomeOtherFieldValue() {
        return someOtherFieldValue;
    }

    public void setSomeOtherFieldValue(int someOtherFieldValue) {
        this.someOtherFieldValue = someOtherFieldValue;
    }
}
