package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Vehicle {
    protected String brand = "Ford";
    public void honk() {
        log.info("Tuut, tuut!");
    }
}

@Slf4j
class Car extends Vehicle {
    private final String modelName = "Mustang";
    public static void main (String... args) {
        Car myFastCar = new Car();
        myFastCar.honk();
        log.info("{} - {}", myFastCar.brand, myFastCar.modelName);
    }
}
