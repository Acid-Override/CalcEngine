package com.pluralsight.calcengine;

abstract class StaticInitializerTest {

    //demo of a static initializer



    //field values

      //initializers

    //constructors

    //methods



    //field values
      private int someFieldValue, anotherFieldValue; //set to private to control state

      //initializers
      static {
          System.out.println("I am a static initializer that is used for all StaticInitializerTests.");
      }


    //constructors

      //default constructor (automatically created if not provided)
      public StaticInitializerTest() {}  //can be public or private, NO return value

      public StaticInitializerTest(int someFieldValue) {
          this.someFieldValue = someFieldValue;
      }


    //methods

    abstract void someFunction ();

    public int getSomeFieldValue() {
        return someFieldValue;
    }
    public void setSomeFieldValue(int someFieldValue) {
        this.someFieldValue = someFieldValue;
    }

    public int getAnotherFieldValue() {
        return anotherFieldValue;
    }
    public void setAnotherFieldValue(int anotherFieldValue) {
        this.anotherFieldValue = anotherFieldValue;
    }
}
