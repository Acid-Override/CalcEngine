package com.playground.MyList.Decorator;

import com.playground.MyList.Decorator.api.MyListV3;

import java.util.List;

public abstract class MyListV3Decorator<T> implements MyListV3<T> {
    MyListV3<T> decoratedMyList;

    protected MyListV3Decorator(MyListV3<T> decoratedMyList) {
        this.decoratedMyList = decoratedMyList;
    }

    @Override
    public boolean add(T item) {
        return decoratedMyList.add(item);
    }

    @Override
    public boolean addAll(List<T> items) {
        return decoratedMyList.addAll(items);
    }

    @Override
    public boolean contains(T item) {
        return decoratedMyList.contains(item);
    }

    @Override
    public int size() {
        return decoratedMyList.size();
    }
}
