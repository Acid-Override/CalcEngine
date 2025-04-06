package com.playground.MyList.MyListV2.api;

import java.util.List;

public interface MyList<T> {
    boolean add(T item);
    boolean addAll(List<T> items);
    boolean contains(T item);
    int size();
}
