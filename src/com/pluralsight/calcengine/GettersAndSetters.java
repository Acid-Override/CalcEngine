package com.pluralsight.calcengine;

import org.junit.jupiter.api.Test;

public class GettersAndSetters {

    @Test
    public void firstTest(){
        GettersAndSetters gettersAndSetters = new GettersAndSetters();
        GettersAndSetters.Node root = gettersAndSetters.new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        System.out.println(root.value + root.right.value + root.left.value);
    }

    public static void main (String... args) {
        GettersAndSetters gettersAndSetters = new GettersAndSetters();
        GettersAndSetters.Node root = gettersAndSetters.new Node(10);
        System.out.println(root.getValue());

    }
    //create a new root node





    private class Node {
        private int value;
        private Node left, right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
    }
}
