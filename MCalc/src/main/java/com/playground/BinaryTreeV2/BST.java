package com.playground.BinaryTreeV2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
public class BST {

    private Node rootNode;

    public static class Node {
        protected int value;
        protected Node left;
        protected Node right;
        protected boolean isRemoved = false;

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
        if (node == null) {
            return null;
        }
        
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

    public Node remove(int val) {
        if (rootNode == null) {
            return null;
        }
        
        // First find the node to mark it as removed
        Node nodeToRemove = find(val);
        if (nodeToRemove != null) {
            nodeToRemove.isRemoved = true;
            // Only remove the node if it currently exists
            rootNode = removeRec(rootNode, val);
        }
        return rootNode;
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
            // case 1: no children
            log.info("REMOVING={}", rootNode);
            log.info("CASE 1, NO CHILDREN CHECK");
            if (rootNode.left == null && rootNode.right == null) {
                log.info("CASE 1, NO CHILDREN");
                return null;
            }
            // case 2: one child
            log.info("CASE 2, ONE CHILD CHECK");
            if (rootNode.left == null && rootNode.right != null) {
                log.info("CASE 2, ONE RIGHT CHILD");
                return rootNode.right;
            } else if (rootNode.right == null && rootNode.left != null) {
                log.info("CASE 2, ONE LEFT CHILD");
                return rootNode.left;
            }
            // case 3: two children
            // get the inorder successor (smallest in the right subtree)
            log.info("CASE 3, TWO CHILDREN");
            rootNode.value = minValue(rootNode.right);
            // delete the inorder successor
            rootNode.right = removeRec(rootNode.right, rootNode.value);
        }
        return rootNode;
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
