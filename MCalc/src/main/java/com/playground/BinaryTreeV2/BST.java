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

    public boolean remove(int val) {
        rootNode = removeRec(rootNode, val);
        return rootNode != null;
    }

    private Node removeRec(Node rootNode, int val) {
        // compare val with rootNode
        if (rootNode == null) {
            return null;
        }

        if (val > rootNode.value) {
            rootNode.right = removeRec(rootNode.right, val);
        } else if (val < rootNode.value) {
            rootNode.left = removeRec(rootNode.left, val);
        } else {
            // val == rootNode.value
            // case 1: no children
            if (rootNode.left == null && rootNode.right == null) {
                return null;
            }
            // case 2: one child
            if (rootNode.left == null) {
                return rootNode.right;
            } else if (rootNode.right == null) {
                return rootNode.left;
            }
            // case 3: two children
            // get the inorder successor (smallest in the right subtree)
            rootNode.value = minValue(rootNode.right);
            // delete the inorder successor
            rootNode.right = removeRec(rootNode.right, rootNode.value);
        }
        return null;
    }

    private int minValue(Node node) {
        int minValue = node.value;
        while (node.left != null) {
            minValue = node.left.value;
            node = node.left;
        }
        return minValue;
    }

    @Override
    public String toString() {
        return "BST{" +
                "rootNode=" + rootNode +
                '}';
    }
}
