package com.pluralsight.calcengine.test;

import com.pluralsight.calcengine.BinarySearchTree;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class BinarySearchTreeTest {

    BinarySearchTree tree;

    @Before
    public void setup() {
        tree = new BinarySearchTree();
    }

    @Test
    public void testOne() {
        ThreadLocalRandom.current().ints(100, 0, 200)
                .forEach(value -> {
                    System.out.println("Value=" + value);
                    tree.insert(value);
                });
        tree.inorder();
    }

    @Test
    public void firstTest() {
        BinarySearchTree.Node node = new BinarySearchTree.Node(10, new BinarySearchTree.Node(20), new BinarySearchTree.Node(30));

        System.out.println(node.getValue());
        System.out.println(node.getLeft().getValue());
        System.out.println(node.getRight().getValue());
    }

}
