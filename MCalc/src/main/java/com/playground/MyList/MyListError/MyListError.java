package com.playground.MyList.MyListError;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyListError<T> {

//    private final List<T> list = new ArrayList<>();
    private Object[] arr = new Object[10];
    protected int index = 0;

    public boolean add(T item) {
        if (index >= arr.length) {
            log.info("INCREASING SIZE");
            increaseSize();
        }
        arr[index++] = item;
        return true;
    }

    private void increaseSize() {
        log.info("ORIGINAL SIZE={}", arr.length);
        Object[] newArr = new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        log.info("NEW SIZE={}", newArr.length);
        arr = newArr;
    }

    public boolean addAll(T... items) {
        boolean result = true;
        for (T item : items) {
            if (result) {
                result = add(item);
            }
        }
        return result;
    }

    public boolean contains(T item) {
        log.info("SEARCHING FOR={}", item);
        for (Object t : arr) {
            log.info("CHECKING={}", t);
            if (t.equals(item)) {
                log.info("FOUND={}", t);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MyListError=[");
        String prefix = "";
        for (Object t : arr) {
            sb.append(prefix).append(t);
            prefix = ", ";
        }
        sb.append("]");
        return sb.toString();
    }
}

