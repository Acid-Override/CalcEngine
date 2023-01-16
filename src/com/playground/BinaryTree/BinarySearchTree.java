package com.playground.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BinarySearchTree {

    private Node root;
    private Integer size = 0;
    private List<Integer> leaves = new ArrayList<>();

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

    public Node deleteLeaf(Integer val) {
        return inorderSearchAndDeleteLeaf(getRoot(), val);
    }

    private Node inorderSearchAndDeleteLeaf(Node node, Integer val) {
        if(null == node)
            return null;

        if (Objects.equals(node.getValue(), val))
        {
            logger.log(Level.INFO, "You found the Value : {0}", node.toString());
            return node;
        }

        if(null != inorderSearchAndDeleteLeaf(node.getLeft(), val))
            node.setLeft(null);
        if(null != inorderSearchAndDeleteLeaf(node.getRight(), val))
            node.setRight(null);

        return null;
    }

    public void createATree(List<Integer> numbers)
    {
        numbers.forEach(System.out::println);
        numbers = numbers.stream().sorted().collect(Collectors.toList());
        numbers.forEach(System.out::println);
    }

    public int sumLeaves(){
        buildLeafList(getRoot());
        System.out.println(leaves.toString());
        return leaves.stream().reduce(0, Integer::sum);
    }

    public Node buildLeafList(Node node) {

        if (null == node)
            return null;

        if(node.getLeft() == null && node.getRight() == null) {
            leaves.add(node.getValue());
            return null;
        }
        buildLeafList(node.getLeft());
        buildLeafList(node.getRight());

        return null;
    }

    public Integer sum(){
        return sumAllNodes(getRoot());
    }
    private Integer sumAllNodes(Node node) {
        Integer result = 0;

        if(null == node)
            return 0;

        result += sumAllNodes(node.getLeft());
        result += node.getValue();
        result += sumAllNodes(node.getRight());

        return result;

    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }



}
