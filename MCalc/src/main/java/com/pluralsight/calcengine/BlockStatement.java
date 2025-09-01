package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockStatement {

    public static void main() {
        int v1 = 10, v2 = 40;
        final int diff;

        if ( v1 > v2 ) {
            diff = v1 - v2;
            log.info("v1 is bigger than v2, diff = " + diff);
        } else {
            diff = v2 - v1;
            log.info("v1 is not bigger than v2, diff = " + diff);
        }
    }
}