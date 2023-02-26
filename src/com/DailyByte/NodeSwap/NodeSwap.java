package com.DailyByte.NodeSwap;

import java.util.Objects;

/**
 * Given the reference to a linked list, head, swap each pair of adjacent nodes and the return a reference to the modified list.
 * <p>
 * Ex: Given a reference to the following linked list…
 * <p>
 * 1->2->3->4, return 2->1->4->3 (1 and 2 have been swapped and 3 and 4 have been swapped)
 */
public class NodeSwap {

    private Node root;
    private int size;

    public Node add(int value) {
        if (Objects.isNull(root)) {
            this.root = new Node(value);
            return getRoot();
        }
        Node next = root;
        while (Objects.nonNull(next.getNode())) {
            next = next.getNode();

        }
        next.setNode(new Node(value));
        return getRoot();
    }
    public Node add(int[] arr) {

        for(int i = 0; i < arr.length; i++) {
            add(arr[i]);
        }
        return getRoot();
    }

    //flip every n
    public Node flipper(Node head) {
        Node copy = new Node(-1);
        Node temp = copy;

        while (Objects.nonNull(head)) {
            temp.setNode(new Node(head.getNode().getValue()));
            temp.getNode().setNode(new Node(head.getValue()));
            temp = temp.getNode().getNode();
            head = head.getNode().getNode();

        }
        return copy.getNode();
    }

    public Node flipper(Node head, int n) {
        Node copy = new Node(-1);
        Node temp = copy;

        while (Objects.nonNull(head)) {
            Node newNode;

            for (int i = n; i > 1; i--) {
                Node currentState = head;
                for (int j = 1; j < i; j++) {
                    if(Objects.nonNull(head.getNode()))
                        head = head.getNode();
                }
                newNode = new Node(head.getValue());
                temp.setNode(newNode);
                temp = temp.getNode();

                //reset head back to original
                head = currentState;
            }
            newNode = new Node(head.getValue());
            temp.setNode(newNode);
            temp = temp.getNode();

            for(int k = 0; k < n; k++) {
                head = head.getNode();
            }

        }
        return copy.getNode();
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }


    @Override
    public String toString() {
        return "NodeSwap{" +
                "root=" + root +
                '}';
    }
}

class Node {
    private int value;
    private Node node;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node node) {
        this(value);
        this.node = node;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", node=" + node +
                '}';
    }
}
