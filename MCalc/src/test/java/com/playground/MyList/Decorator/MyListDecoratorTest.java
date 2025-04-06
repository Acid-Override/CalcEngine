package com.playground.MyList.Decorator;

import com.playground.MyList.Decorator.api.MyListV3;
import com.playground.MyList.Decorator.api.MyListV3Counter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyListDecoratorTest {

    CounterDecorator<Integer> list;

    @BeforeEach
    void setUp() {

    }

    @Test
    void addWithCounterAndLogging() {
        list =
                new CounterDecorator<>(
                        new LoggingDecorator<>(
                                new MyListV3Impl<>()));
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        assertEquals(4, list.getCounter(), "List size should be 4 after adding 4 elements");
    }

    @Test
    void addWithValidationAndCounterAndLogging() {
        CounterDecorator<Integer> validationDecoratorList =
                new CounterDecorator<>(
                        new ValidationDecorator<>(
                                new LoggingDecorator<>(
                                        new MyListV3Impl<>()
                                )
                        )
                );

        validationDecoratorList.add(10);
        validationDecoratorList.add(20);
        validationDecoratorList.add(30);
        validationDecoratorList.add(40);

        assertEquals(4, validationDecoratorList.getCounter(), "List size should be 4 after adding 4 elements");
    }

    @Test
    void addAllWithValidationAndCounterAndLogging() {
        CounterDecorator<Integer> validationDecoratorList =
                new CounterDecorator<>(
                        new ValidationDecorator<>(
                                new LoggingDecorator<>(
                                        new MyListV3Impl<>()
                                )
                        )
                );

        validationDecoratorList.addAll(
                List.of(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110)
        );

        assertEquals(11, validationDecoratorList.getCounter(), "List size should be 10 after adding 10 elements");
    }

    @Test
    void getCounter() {
    }
}