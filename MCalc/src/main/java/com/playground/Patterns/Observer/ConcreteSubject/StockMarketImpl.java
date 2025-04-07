package com.playground.Patterns.Observer.ConcreteSubject;

import com.playground.Patterns.Observer.ObserverInterface.StockObserver;
import com.playground.Patterns.Observer.SubjectInterface.StockMarket;

import java.util.ArrayList;
import java.util.List;

public class StockMarketImpl implements StockMarket {
    List<StockObserver> stockObservers = new ArrayList<>();
    @Override
    public void register(StockObserver stockObserver) {
        stockObservers.add(stockObserver);
    }

    @Override
    public void unregister(StockObserver stockObserver) {
        stockObservers.remove(stockObserver);
    }

    @Override
    public void notifyObservers(String stockSymbol, double stockPrice) {
        for (StockObserver stockObserver : stockObservers) {
            stockObserver.update(stockSymbol, stockPrice);
        }
    }

    // Simulate stock price changes
    public void updateStockPrice(String stockSymbol, double stockPrice) {
        notifyObservers(stockSymbol, stockPrice);
    }
}
