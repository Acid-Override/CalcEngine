package com.playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @Test
    void createNewNode() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        assertEquals(10, pwg.getRoot().getValue());
    }
     @Test
    void createTwoNodes() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        pwg.add(20);
        assertEquals(20, pwg.getRoot().getNode().getValue());
     }
     @Test
    void createThreeNodes() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        assertEquals(30, pwg.getRoot().getNode().getNode().getValue());
     }
     @Test
    void printAllValues() {
        SinglyLinkedList pwg = new SinglyLinkedList();
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
        SinglyLinkedList pwg = new SinglyLinkedList();
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
     void shiftANode() {
        SinglyLinkedList pwg = new SinglyLinkedList();
         pwg.add(10);
         pwg.add(20);
         pwg.add(30);
         pwg.add(40);
         pwg.add(50);
         pwg.shift();
         assertEquals(20, pwg.getRoot().getValue());
         assertEquals(4, pwg.getSize());
     }

     @Test
    void pushANode() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        pwg.push(5);
        assertEquals(5, pwg.getRoot().getValue());
     }
     @Test
    void checkSize() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        pwg.add(40);
        pwg.pop();
        pwg.printValues();
        assertEquals(3, pwg.getSize());
     }
     @Test
    void popMoreThanAvailable() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        pwg.pop();
        pwg.pop();
        pwg.pop();
        pwg.printValues();
     }
     @Test
    void removeEndValue() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        pwg.add(40);
        pwg.add(50);
        pwg.add(60);
        pwg.remove(60);
        assertEquals(5, pwg.getSize());
     }
     @Test
    void removeMiddleValue() {
        SinglyLinkedList pwg = new SinglyLinkedList();
        pwg.add(10);
        pwg.add(20);
        pwg.add(30);
        pwg.add(40);
        pwg.remove(20);
        pwg.printValues();
        assertEquals(3, pwg.getSize());
        assertEquals(10, pwg.getRoot().getValue());
        assertNotEquals(20, pwg.getRoot().getNode().getValue());
        assertEquals(30, pwg.getRoot().getNode().getValue());
        assertEquals(40, pwg.getRoot().getNode().getNode().getValue());
     }
}