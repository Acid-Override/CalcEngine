package com.playground.MyList.MyListV2;

import com.playground.MyList.MyListV2.api.MyList;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
public class MyListV2Impl<T> implements MyList<T> {

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
    public boolean contains(Object item) {
        return list.contains(item);
    }

    @Override
    public int size() {
        return list.size();
    }

}
