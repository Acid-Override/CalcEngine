package com.pluralsight.calcengine;

import java.util.ArrayList;

public class ArrayListExample {

    public static void main(String[] args) {


        ArrayList<String> testList = new ArrayList<>();
        testList.add("John");
        testList.add("Lily");
        testList.add("Samantha");
        testList.add("Tony");

        testList.remove("Tony");
        testList.set(2, "BoB");

        int size = testList.size();
        System.out.println(size);

        System.out.println(testList.toString()
        );
    }
}
