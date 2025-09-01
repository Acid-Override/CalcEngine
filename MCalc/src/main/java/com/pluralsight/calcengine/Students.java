package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Students {

    public static void main(String[] args) {
        int students = 150;
        int rooms = 2;

        log.info("Crowded");
        studentRoom(new String[]{"Hello"});
    }

    public static void studentRoom(String[] args) {
        double students = 30.0d, rooms = 4.0d;

        log.info(String.valueOf(students));
        log.info(String.valueOf(rooms));
        double avg = students / rooms;
        log.info(String.valueOf(avg));
    }
}
