package com.linkedinlearning.JavaArrays;

import java.util.Arrays;

public class CustomArrayList<D> {

    private int size = 0;
    private static final int DEFAULT_CAPACITY = 2;
    private Object elements[];

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
}

    public D get (int i) {
        if (i < 0 && i >= size) {
            System.out.println("Invalid index");
            return null;
        }
            return (D) elements[i];
    }

    public void add (D item) {
        elements[size] = item;
        size++;

        if ( elements.length == size) {
            increaseCapacity(item);
        }
    }

    private void increaseCapacity(D item) {
        int newSize = elements.length + 2;
        elements = new Object[newSize];
        Arrays.copyOf(elements, newSize);
    }

    public int size() {
        return size;
    }
}
