package com.pluralsight.calcengine;

public class Students {

    public static void main(String[] args) {
        int students = 150;
        int rooms = 2;

        if(rooms != 0 && students/rooms > 30)
            System.out.println("Crowded");
        studentRoom(new String[]{"Hello"});
    }

    public static void studentRoom(String[] args) {
        double students = 30.0d, rooms = 4.0d;

        if ( rooms > 0.0d ) {
            System.out.println(students);
            System.out.println(rooms);
            double avg = students / rooms;
            System.out.println(avg);
        }
    }
}
