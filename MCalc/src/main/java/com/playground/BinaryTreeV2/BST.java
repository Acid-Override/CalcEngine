package com.playground.BinaryTreeV2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BST {

    private Node rootNode;

    public static class Node {
        protected int value;
        protected Node left;
        protected Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    public Node add(int val) {
        rootNode = addRec(rootNode, val);
        return rootNode;
    }

    private Node addRec(Node node, int val) {
        if (node == null) {
            node = new Node(val);
            return node;
        }

        if (val > node.value) {
            node.right = addRec(node.right, val);
        }
        if (val < node.value) {
            node.left = addRec(node.left, val);
        }
        return node;
    }

    public Node find(int val) {
        return findRec(rootNode, val);
    }

    private Node findRec(Node node, int val) {
        if (node.value == val) {
            return node;
        }
        if (val > node.value) {
            return findRec(node.right, val);
        }
        if (val < node.value) {
            return findRec(node.left, val);
        }
        return null;
    }

    @Override
    public String toString() {
        return "BST{" +
                "rootNode=" + rootNode +
                '}';
    }
}
