package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Flight {
    //setting passengers and seats to private protects state
    // only public methods may modify state

    //both class AND constructor must be public
    private int passengers;
    private int seats = 150;

    private static int allPassengers;  //will default to 0

    public Flight() {}

    public void add1Passenger() {
        if ( passengers < seats) {
            passengers++;
            allPassengers++;
        } else handleTooMany();
    }

    private void handleTooMany() {
        log.info("Too many passengers");
    }
    public static int getAllPassengers() {
        return allPassengers;
    }

}
