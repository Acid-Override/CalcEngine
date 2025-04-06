package com.playground.MyList.MyListError;

import lombok.Getter;

import java.util.List;

@Getter
public class MyListErrorWithCounter<T> extends MyListError<T> {
    private int timesAdded = 0;

    @Override
    public boolean add(T item) {
        timesAdded++;
        return super.add(item);
    }

    @Override
    public final boolean addAll(T... items) {
        return super.addAll(items);
    }

    @Override
    public boolean contains(T item) {
        return super.contains(item);
    }

    @Override
    public int size() {
        return super.size();
    }
}
