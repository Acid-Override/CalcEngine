package com.playground.SinglyLinkedList;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;

import java.util.Objects;
import java.util.logging.Level;

@Slf4j
@Data
public class SinglyLinkedList {

    private Node root;
    private Integer size = 0;


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
            log.info("Unable to POP : Root is NULL.");
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
        log.info("Current size: " + size);
        Node currentNode = getRoot();
        if(currentNode != null) {
            while(null != currentNode.getNode()) {
                log.info("Current Node Value:{}", currentNode.getValue());
                currentNode = currentNode.getNode();
            }
            log.info("Node Value:{}", currentNode.getValue());
        } else {
            log.info("Root is NULL.");
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
            log.info("Root is NULL");
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
                log.info((Marker) Level.INFO, "Removing the node : {}", currentNode);
            }
            currentNode = currentNode.getNode();
        }
        root = pwg.getRoot();
        size = pwg.getSize();
    }
}
