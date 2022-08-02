package com.pluralsight.calcengine;

public class Flight {
    //setting passengers and seats to private protects state
    // only allowing public methods to modify state

    //both class AND constructor must be public
    private int passengers;
    private int seats;

    public Flight() {}

    public void add1Passenger() {
        if ( passengers < seats)
          passengers++;
        else handleTooMany();
    }

    private void handleTooMany() {
        System.out.println("Too many passengers");
    }

}
