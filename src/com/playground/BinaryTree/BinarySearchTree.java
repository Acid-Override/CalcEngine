package com.playground.BinaryTree;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BinarySearchTree {

    private Node root;
    private Integer size;

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




    public void add(Node node, Integer val)
    {
        if (null == node)
        {
            setRoot(new Node(val));
            logger.log(Level.INFO, "Creating a new BST {0}.", getRoot());
            return;
        }
        if (val > node.getValue())
        {
            //insert at right node

            add(node.getRight(), val);
        } else
        {
            //insert at left node
            add(node.getLeft(), val);
        }

    }

}
