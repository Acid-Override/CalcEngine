package com.playground.Patterns.Observer.ConcreteObserver;

import com.playground.Patterns.Observer.ObserverInterface.StockObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Investor implements StockObserver {

    String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double stockPrice) {
        log.info(name + " received an update for " + stockSymbol + ": " + stockPrice);
    }
}
