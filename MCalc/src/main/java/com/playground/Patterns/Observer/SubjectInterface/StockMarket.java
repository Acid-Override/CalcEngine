package com.playground.Patterns.Observer.SubjectInterface;

import com.playground.Patterns.Observer.ObserverInterface.StockObserver;

public interface StockMarket {
    void register(StockObserver stockObserver);
    void unregister(StockObserver stockObserver);
    void notifyObservers(String stockSymbol, double stockPrice);
}
