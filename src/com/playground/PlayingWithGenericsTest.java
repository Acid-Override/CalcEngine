package com.playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayingWithGenericsTest {

    @Test
    void createNewNode() {
        PlayingWithGenerics pwg = new PlayingWithGenerics();
        pwg.add(10);
        assertEquals(10, pwg.getRoot().getValue());
    }
     @Test
    void createTwoNodes() {
        PlayingWithGenerics pwg = new PlayingWithGenerics();
        pwg.add(10);
        pwg.add(20);
        assertEquals(20, pwg.getRoot().getNode().getValue());
     }
     @Test
    void createThreeNodes() {
        PlayingWithGenerics pwg = new PlayingWithGenerics();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        assertEquals(30, pwg.getRoot().getNode().getNode().getValue());
     }
     @Test
    void printAllValues() {
        PlayingWithGenerics pwg = new PlayingWithGenerics();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        pwg.add(40);
        pwg.add(50);
        pwg.add(60);
        pwg.printValues();
     }
     @Test
     void popANode() {
        PlayingWithGenerics pwg = new PlayingWithGenerics();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        pwg.add(40);
        pwg.add(50);
        Integer result = pwg.pop();
        assertEquals(50, result);
        pwg.printValues();
     }

     @Test
    void pushANode() {
        PlayingWithGenerics pwg = new PlayingWithGenerics();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        pwg.push(5);
        assertEquals(5, pwg.getRoot().getValue());
     }
}