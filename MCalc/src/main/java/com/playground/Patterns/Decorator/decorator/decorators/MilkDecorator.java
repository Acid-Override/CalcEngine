package com.playground.Patterns.Decorator.decorator.decorators;

import com.playground.Patterns.Decorator.api.Coffee;
import com.playground.Patterns.Decorator.decorator.base.CoffeeDecorator;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}
