package com.playground;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SinglyLinkedList {

    private Node root;
    private Integer size = 0;

    private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


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

    public void add (Integer val) {
        if (null == root) {
            root = new Node(val);
        } else {
            Node newNode = new Node(val);
            Node testNode = root;
            while (testNode.getNode() != null)
            {
                testNode = testNode.getNode();
            }
            testNode.setNode(newNode);
        }
        size++;
    }

    public Integer pop() {
        Integer resultNodeValue;
        if ( null == root) {
            System.out.println("Unable to POP : Root is NULL.");
            return null;
        } else if (root.getNode() != null){
            Node currentNode = root;
            while(currentNode.getNode().getNode() != null) {
                currentNode = currentNode.getNode();
            }
            resultNodeValue = currentNode.getNode().getValue();
            currentNode.setNode(null);
        } else {
            resultNodeValue = root.getValue();
            setRoot(null);
        }
        size--;
        return resultNodeValue;
    }

    public void printValues() {
        System.out.println("Current size: " + size);
        Node currentNode = getRoot();
        if(currentNode != null) {
            while(null != currentNode.getNode()) {
                System.out.println(currentNode.getValue());
                currentNode = currentNode.getNode();
            }
            System.out.println(currentNode.getValue());
        } else {
            System.out.println("Root is NULL.");
        }
    }

    public void push(Integer val) {
        if(null == root) {
            root = new Node(val);
        } else {
            Node newRoot = new Node(val);
            newRoot.setNode(root);
            root = newRoot;
        }
        size++;
    }

    public void shift() {
        root = root.getNode();
        size--;
    }

    public void remove(Integer val) {
        if(null == root) {
            logger.info("Root is NULL");
            return;
        }
        if(Objects.equals(root.getValue(), val)) {
            shift();
            return;
        }

        SinglyLinkedList pwg = new SinglyLinkedList();
        Node currentNode = root;
        while(null != currentNode) {
            if(!Objects.equals(currentNode.getValue(), val)) {
                pwg.add(currentNode.getValue());
            } else {
                logger.log(Level.INFO, "Removing the node : {0}", currentNode);
            }
            currentNode = currentNode.getNode();
        }
        root = pwg.getRoot();
        size = pwg.getSize();
    }
}
