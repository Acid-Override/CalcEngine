package org.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("Hello world!");
        log.info("LOG -> Hello world!");
    }
}