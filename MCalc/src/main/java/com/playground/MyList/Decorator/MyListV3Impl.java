package com.playground.MyList.Decorator;

import com.playground.MyList.Decorator.api.MyListV3;

import java.util.ArrayList;
import java.util.List;

public class MyListV3Impl<T> implements MyListV3<T> {

    private final List<T> list = new ArrayList<>();

    @Override
    public boolean add(T item) {
        return list.add(item);
    }

    @Override
    public boolean addAll(List<T> items) {
        return list.addAll(items);
    }

    @Override
    public boolean contains(T item) {
        return list.contains(item);
    }

    @Override
    public int size() {
        return list.size();
    }
}
