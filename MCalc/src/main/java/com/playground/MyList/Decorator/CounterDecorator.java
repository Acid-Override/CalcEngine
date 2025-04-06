package com.playground.MyList.Decorator;

import com.playground.MyList.Decorator.api.MyListV3;
import com.playground.MyList.Decorator.api.MyListV3Counter;
import lombok.Getter;

import java.util.List;

public class CounterDecorator<T> extends MyListV3Decorator<T> implements MyListV3Counter<T> {

    private int counter = 0;

    public CounterDecorator(MyListV3<T> decoratedMyList) {
        super(decoratedMyList);
    }

    @Override
    public boolean add(T item) {
        counter++;
        return super.add(item);
    }

    @Override
    public boolean addAll(List<T> items) {
        counter += items.size();
        return super.addAll(items);
    }

    @Override
    public int getCounter() {
        return counter;
    }
}
