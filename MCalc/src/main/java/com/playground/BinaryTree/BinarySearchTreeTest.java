package com.playground.BinaryTree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Test
    void createATreeWithList() {
        List<Integer> numbers = List.of(60, 80, 10, 20, 90, 70, 40, 30, 50);

        BinarySearchTree bst = new BinarySearchTree();
        bst.createATree(numbers);

        //10, 20, 30, 40, 50, 60, 70, 80, 90
        //50, 30, 80, 20, 40, 70, 90, 10, 60
    }
    @Test
    void deepestLeafSum() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        bst.insert(2);
        bst.insert(5);
        assertEquals(bst.sumLeaves(), 7);
    }
    @Test
    void multiNodeDeepestLeafSum() {
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
        int result = bst.sumLeaves();
        System.out.println(result);
    }
    @Test
    void sumAllNodesInTree() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        bst.insert(2);
        bst.insert(5);
        Integer sum = bst.sum();
        assertEquals(11, sum);
    }
    @Test
    void sumAllNodesInTreeWithManyNodes() {
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
        Integer sum = bst.sum();
        System.out.println("Total sum is : " + sum);
        assertEquals(450, sum);
    }
}
