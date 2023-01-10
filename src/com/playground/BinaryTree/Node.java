package com.playground.BinaryTree;

import java.util.Objects;

public class Node {
    private Integer value;
    private Node right;
    private Node left;

    public Node() {}

    public Node (Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value) && Objects.equals(right, node.right) && Objects.equals(left, node.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, right, left);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", right=" + right +
                ", left=" + left +
                '}';
    }
}
