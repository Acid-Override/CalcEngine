package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ForLoop {

    public static void main(String[] args) {
        for (var i = 0; i < 10; i++ ) {
            log.info(String.valueOf(i));
        }
    }
}
