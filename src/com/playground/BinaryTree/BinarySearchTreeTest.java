package com.playground.BinaryTree;

import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {

    @Test
    void createANewRoot() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(bst.getRoot(), 10);
        bst.toString();
    }
    @Test
    void createThreeNodesInCorrectOrder() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(bst.getRoot(), 10);
        bst.add(bst.getRoot(), 5);
        bst.add(bst.getRoot(), 15);
        bst.toString();
    }
}
