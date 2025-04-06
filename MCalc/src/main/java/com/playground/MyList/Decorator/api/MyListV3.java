package com.playground.MyList.Decorator.api;

import java.util.List;

public interface MyListV3<T> {
    boolean add(T item);
    boolean addAll(List<T> items);
    boolean contains(T item);
    int size();
}
