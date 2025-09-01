package com.playground.PostgreSQL;

import lombok.extern.slf4j.Slf4j;

import com.pluralsight.calcengine.Main;

@Slf4j
public class HelloWorld {


    public static void main(String[] args) {
        boolean runningInIntellij = false;
        try {
            runningInIntellij = Main.class.getClassLoader().loadClass("com.intellij.rt.execution.application.AppMainV2") != null;
        } catch (ClassNotFoundException e) {}
        if (runningInIntellij) {
            log.info("Happy Coding!"); //
        } else {
            log.info("Meh, coding");
        }
    }
}
