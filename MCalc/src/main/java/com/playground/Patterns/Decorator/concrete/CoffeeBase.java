package com.playground.Patterns.Decorator.concrete;

import com.playground.Patterns.Decorator.api.Coffee;

public class CoffeeBase implements Coffee {

    @Override
    public String getDescription() {
        return "Plain Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}
