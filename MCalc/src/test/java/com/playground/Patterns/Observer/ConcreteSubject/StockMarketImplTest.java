package com.playground.Patterns.Observer.ConcreteSubject;

import com.playground.Patterns.Observer.ConcreteObserver.Investor;
import com.playground.Patterns.Observer.ObserverInterface.StockObserver;
import com.playground.Patterns.Observer.SubjectInterface.StockMarket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockMarketImplTest {

    @Test
    void testStockMarket() {
        StockMarketImpl stockMarket = new StockMarketImpl();

        StockObserver investor1 = new Investor("Investor 1");
        StockObserver investor2 = new Investor("Investor 2");

        stockMarket.register(investor1);
        stockMarket.register(investor2);

        stockMarket.updateStockPrice("AAPL", 100.0);
        stockMarket.updateStockPrice("GOOG", 200.0);

        stockMarket.unregister(investor1);

        stockMarket.updateStockPrice("MSFT", 300.0);
    }

    @Test
    void register() {
    }

    @Test
    void unregister() {
    }

    @Test
    void notifyObservers() {
    }

    @Test
    void updateStockPrice() {
    }
}