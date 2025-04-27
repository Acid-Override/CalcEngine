package com.playground.PostgreSQL;

import com.pluralsight.calcengine.Main;

public class HelloWorld {


    public static void main(String[] args) {
        boolean runningInIntellij = false;
        try {
            runningInIntellij = Main.class.getClassLoader().loadClass("com.intellij.rt.execution.application.AppMainV2") != null;
        } catch (ClassNotFoundException e) {}
        if (runningInIntellij) {
            System.out.println("Happy Coding!"); //
        } else {
            System.out.println("Meh, coding");
        }
    }
}
