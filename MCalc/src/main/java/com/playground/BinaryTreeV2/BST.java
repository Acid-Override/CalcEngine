package com.playground.BinaryTreeV2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
public class BST {

    private Node rootNode;
    private int size = 0;

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
        size++;
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
            size--; // Decrement size when a node is found and marked as removed
        }
        
        // Then perform the actual removal
        rootNode = removeRec(rootNode, val);
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

    /**
     * Performs an in-order traversal of the BST and stores the values in the provided array.
     * In-order traversal visits nodes in ascending order: left subtree, current node, right subtree.
     * 
     * @param node The current node being visited
     * @param values The array to store the values
     * @param index The current index in the array
     * @return The next index to use
     */
    public int inOrderSearch(Node node, int[] values, int index) {
        if (node == null) {
            return index;
        }
        
        // First, visit left subtree
        index = inOrderSearch(node.left, values, index);
        
        // Then, visit current node
        values[index] = node.value;
        index++;
        
        // Finally, visit right subtree
        return inOrderSearch(node.right, values, index);
    }

    /**
     * Rebalances the BST by creating a new tree with nodes added in an order
     * that ensures the tree is balanced.
     * 
     * @return The root node of the rebalanced tree
     */
    public Node rebalance() {
        // If tree is empty, nothing to rebalance
        if (rootNode == null) {
            return null;
        }
        
        // Store the current size before rebalancing
        int currentSize = size;
        
        // Get all values in sorted order
        int[] values = new int[currentSize];
        inOrderSearch(rootNode, values, 0);
        log.info("Values={}", values);
        
        // Reset the tree
        rootNode = null;
        size = 0;
        
        // Rebuild the tree in a balanced way
        rootNode = buildBalancedTree(values, 0, values.length - 1);
        log.info("Rebalanced tree={}", rootNode);
        
        return rootNode;
    }
    
    /**
     * Recursively builds a balanced BST from a sorted array of values.
     * 
     * @param values The sorted array of values
     * @param start The start index of the current subarray
     * @param end The end index of the current subarray
     * @return The root node of the balanced subtree
     */
    private Node buildBalancedTree(int[] values, int start, int end) {
        // Base case: empty subarray
        if (start > end) {
            return null;
        }
        
        // Find the middle element to make it the root
        int mid = (start + end) / 2;
        
        // Create a new node with the middle element
        Node node = new Node(values[mid]);
        size++; // Increment size as we add a node
        
        // Recursively build left and right subtrees
        node.left = buildBalancedTree(values, start, mid - 1);
        node.right = buildBalancedTree(values, mid + 1, end);
        
        return node;
    }

    @Override
    public String toString() {
        return "BST{" +
                "rootNode=" + rootNode +
                ", size=" + size +
                '}';
    }
}
