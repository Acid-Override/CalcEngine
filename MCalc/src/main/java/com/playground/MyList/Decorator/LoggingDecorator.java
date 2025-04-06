package com.playground.MyList.Decorator;

import com.playground.MyList.Decorator.api.MyListV3;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class LoggingDecorator<T> extends MyListV3Decorator<T> {
    public LoggingDecorator(MyListV3<T> decoratedMyList) {
        super(decoratedMyList);
    }

    @Override
    public boolean add(T item) {
        log.info("Adding item: {}", item);
        return super.add(item);
    }

    @Override
    public boolean addAll(List<T> items) {
        log.info("Adding items: {}", items);
        return super.addAll(items);
    }

    @Override
    public boolean contains(T item) {
        log.info("Checking for item: {}", item);
        return super.contains(item);
    }

    @Override
    public int size() {
        log.info("Getting size");
        return super.size();
    }
}
