package com.playground.MyList.MyListV2;

import com.playground.MyList.MyListV2.api.MyList;

import java.util.List;

public class MyListV2WithCounterImpl<T> implements MyList<T> {
    private final MyListV2Impl<T> list = new MyListV2Impl<>();
    private int counter = 0;

    @Override
    public boolean add(T item) {
        counter++;
        return list.add(item);
    }

    @Override
    public boolean addAll(List<T> items) {
        counter += items.size();
        return list.addAll(items);
    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
