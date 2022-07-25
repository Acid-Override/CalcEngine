package com.pluralsight.calcengine;

public class BlockStatement {

    public static void main(String[] args) {
        int v1 = 10, v2 = 40;
        final int diff;

        if ( v1 > v2 ) {
            diff = v1 - v2;
            System.out.println("v1 is bigger than v2, diff = " + diff);
        } else {
            diff = v2 - v1;
            System.out.println("v1 is not bigger than v2, diff = " + diff);
        }
    }
}