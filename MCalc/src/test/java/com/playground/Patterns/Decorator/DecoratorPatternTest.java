package com.playground.Patterns.Decorator;

import com.playground.Patterns.Decorator.api.Coffee;
import com.playground.Patterns.Decorator.concrete.CoffeeBase;
import com.playground.Patterns.Decorator.decorator.decorators.MilkDecorator;
import com.playground.Patterns.Decorator.decorator.decorators.SugarDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void decoratorPatternTest_coffeeWithMilkAndSugar() {
        Coffee drink = new SugarDecorator(new MilkDecorator(new CoffeeBase()));
        log.info("Description: {}", drink.getDescription());
        log.info("Cost: {}", drink.getCost());
    }

    @Test
    void decoratorPatternTest_coffeeWithSugar() {
        Coffee drink = new SugarDecorator(new CoffeeBase());
        log.info("Description: {}", drink.getDescription());
        log.info("Cost: {}", drink.getCost());
    }
}
