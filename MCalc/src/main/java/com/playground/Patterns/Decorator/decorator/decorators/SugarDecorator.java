package com.playground.Patterns.Decorator.decorator.decorators;

import com.playground.Patterns.Decorator.api.Coffee;
import com.playground.Patterns.Decorator.decorator.base.CoffeeDecorator;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }
}
