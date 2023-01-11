package com.playground.BinaryTree;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinarySearchTree {

    private Node root;
    private Integer size = 0;

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public BinarySearchTree () {}

    public Node getRoot() {
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }

    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }

    public void insert(Integer val)
    {
        this.root = add(getRoot(), val);
    }

    private Node add(Node node, Integer val)
    {
        if (null == node)
        {
            Node newNode = new Node(val);
            size++;
            return newNode;
        }
        if (val > node.getValue())
        {
            node.setRight(add(node.getRight(), val));
        }
        else
        {
            node.setLeft(add(node.getLeft(), val));
        }

        return node;
    }

    public void printValues(){
        inorderScan(getRoot());
    }

    private void inorderScan(Node node)
    {
        if(null == node){
            return;
        }
        inorderScan(node.getLeft());
        System.out.println(node.getValue());
        inorderScan(node.getRight());
    }

    public Node search(Integer val) {
        return inorderSearch(getRoot(), val);
    }

    private Node inorderSearch(Node node, Integer val) {
        if(null == node)
            return null;

        if (Objects.equals(node.getValue(), val))
        {
            logger.log(Level.INFO, "You found the Value : {0}", node.toString());
            return node;
        }

        inorderSearch(node.getLeft(), val);
        inorderSearch(node.getRight(), val);

        return null;
    }


    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }


}
