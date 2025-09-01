package com.pluralsight.calcengine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class BinarySearchTree {

//    @Test
//    public void firstTest() {
//        Node node = new Node(10, new Node (20), new Node(30));
//
//        log.info(String.valueOf(node.value));
//        log.info(node.getLeft());
//        log.info(node.getRight());
//    }
//    @Test
//    public void secondTest() {
//
//    }

    @Data
    static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node () {}

        public Node (Integer value) {
            this.value = value;
        }
        public Node (Integer value, Node left, Node right) {
            this(value);
            this.left = left;
            this.right = right;
        }

    }

    Node root;

    BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree (int Value) {
        root = new Node(Value);
    }

    void insert (int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        //insert new node into tree using recursion

        // check provided key with current root value
          //if current value is null
            //insert new node
          //else if
            //key is less than root value
              //recurse on left node
          //else if
            //key is more than root value
              //recures on right node

        if ( null == root ) {
            root = new Node(key);
            return root;
        }

        else if (key < root.getValue()) {
            root.left = insertRec(root.left, key);
        }
        else if (key > root.getValue()) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    private void inorder() {
        //helper function to send to recursive function
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        //recursively iterate through tree and print out value.

        if (root != null ) {
            inorderRec(root.left);
            log.info(String.valueOf(root.getValue()));
            inorderRec(root.right);
        }
    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /*
        let us create the following BST
                 50
               /    \
              30     70
             /  \   /  \
            20  40  60  80
         */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        //print inorder traversal of the BST
        tree.inorder();
    }



}
