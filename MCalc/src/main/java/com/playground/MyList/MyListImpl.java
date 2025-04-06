package com.playground.MyList;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface MyList<T> {
    boolean add(T item);
    boolean addAll(List<T> items);
    boolean contains(T item);

    int size();
}

@Getter
public class MyListImpl<T> implements MyList<T> {

    private final List<T> list = new ArrayList<>();

    @Override
    public boolean add(T item) {
        return list.add(item);
    }

    @Override
    public final boolean addAll(List<T> items) {
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
