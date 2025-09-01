package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
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
        log.info(String.valueOf(size));

        log.info("TestList:{}",testList);
    }
}
