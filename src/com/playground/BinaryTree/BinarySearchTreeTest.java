package com.playground.BinaryTree;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {

    @Test
    void createANewRoot() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.toString();
    }
    @Test
    void createThreeNodesInCorrectOrder() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        System.out.println(bst.toString());
    }
    @Test
    void createFourNodesUsingRecursion() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(7);
        bst.printValues();
        System.out.println(bst.toString());
    }

    @Test
    void printAllValuesInorder() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(40);
        bst.insert(60);
        bst.insert(45);
        bst.insert(30);
        bst.insert(35);
        bst.insert(55);
        bst.insert(70);
        bst.insert(65);
        bst.printValues();
        System.out.println(bst.toString());
    }
    @Test
    void insertAValue(){
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(40);
        bst.insert(60);
        bst.printValues();
        System.out.println(bst.toString());
    }
    @Test
    void findAValue() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(40);
        bst.insert(60);
        bst.insert(45);
        bst.insert(30);
        bst.insert(35);
        bst.insert(55);
        bst.insert(70);
        bst.insert(65);
        bst.deleteLeaf(35);
    }
}
