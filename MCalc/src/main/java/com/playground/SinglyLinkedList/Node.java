package com.playground.SinglyLinkedList;

public class Node {

    private Integer value;

    private Node node;

    public Node() {}

    public Node(Integer value) {
        this.value = value;
    }
    public Node(Integer value, Node node) {
        this.value = value;
        this.node = node;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
