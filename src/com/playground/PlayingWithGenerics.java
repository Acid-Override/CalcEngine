package com.playground;

public class PlayingWithGenerics {

    private Node root;
    private Integer size;

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
            System.out.println("Null Root");
            return null;
        } else {
            Node currentNode = root;
            while(currentNode.getNode().getNode() != null) {
                currentNode = currentNode.getNode();
            }
            resultNodeValue = currentNode.getNode().getValue();
            currentNode.setNode(null);
        }
        size--;
        return resultNodeValue;
    }

    public void printValues() {
        Node currentNode = getRoot();
        while(null != currentNode.getNode()) {
            System.out.println(currentNode.getValue());
            currentNode = currentNode.getNode();
        }
        System.out.println(currentNode.getValue());
    }

    public void push(Integer val) {
        if(null == root) {
            root = new Node(val);
        } else {
            Node newRoot = new Node(val);
            newRoot.setNode(root);
            root = newRoot;
        }
    }
}
