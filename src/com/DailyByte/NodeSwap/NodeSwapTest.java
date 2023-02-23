package com.DailyByte.NodeSwap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeSwapTest {

    NodeSwap ns;

    @BeforeEach
    void setUp() {
        ns = new NodeSwap();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addTest() {
        ns.add(10);
        System.out.println(ns.toString());
        ns.add(20);
        System.out.println(ns.toString());
        ns.add(30);
        System.out.println(ns.toString());
    }
    @Test
    void addArrayTest() {
        ns.add(new int[] {10, 20, 30, 40, 50, 60, 70, 80});
        System.out.println(ns.toString());
    }

    @Test
    void nFlipInitTest() {
        ns.add(10);
        ns.add(20);
        ns.add(30);
        ns.add(40);
        Node result = ns.flipper(ns.getRoot(), 2);
    }

    @Test
    void nFlipperTest() {
        ns.add(10);
        ns.add(20);
        ns.add(30);
        ns.add(40);
        ns.nFlipper(ns.getRoot(), 2);
    }
    @Test
    void nFlipperBigSet() {
        ns.add(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
        ns.nFlipper(ns.getRoot(), 5);
    }
    @Test
    void nFlipperUnEven() {
        ns.add(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110});
        ns.nFlipper(ns.getRoot(), 5);
    }
}