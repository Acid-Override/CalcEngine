package com.DailyByte.TreeReconstruction;

import lombok.*;

import java.util.Arrays;
import java.util.Objects;

/** #####################  INCOMPLETE ############################
 *
 * Given two integer arrays, preorder and inorder, that represent the preorder and inorder traversal of the same binary tree respectively,
 * construct and return the binary tree that they represent.
 * Note: Both preorder and inorder consist of unique values.
 *
 * Ex: Given the following preorder and inorder…
 *
 * preorder = [1, 2, 3], inorder = [2, 1, 3], return the reference to a binary tree that looks as follows...
 *         1
 *       /   \
 *      2     3
 */


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TreeReconstruction {

    private int[] preorder;
    private int[] inorder;
    private int index = 0;
    private Node root;

    public TreeReconstruction(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
    }

    public Node add (int val) {
       return root = addValue(root, val);
    }

    private Node addValue(Node node, int val) {
        if (Objects.isNull(node))
            return new Node(val);

        if(val > node.getValue())
            node.setRight(addValue(node.getRight(), val));
        if(val < node.getValue())
            node.setLeft(addValue(node.getLeft(), val));

        return node;
    }

    public void treeRecon () {
        Arrays.stream(preorder).forEach(val -> search(root, val));
    }

    private Node search (Node node, int val) {
        if (Objects.isNull(node)) {
            index++;
            setRoot(new Node(val, 0));
            return root;
        }



        if(Objects.isNull(node.getLeft())) {
            node.setLeft(new Node(val));
            index++;
            return node;
        }
        if(Objects.isNull(node.getRight())) {
            node.setRight(new Node(val));
            index++;
            return node;
        }



        if(Objects.isNull(node.getLeft().getLeft()) || Objects.isNull(node.getLeft().getRight()))
            search(node.getLeft(), val);
        else if (Objects.isNull(node.getRight().getLeft()) || Objects.isNull(node.getRight().getRight()))
            search(node.getRight(), val);
        else
            search(node.getLeft(), val);
        return node;
    }
}

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
class Node {
    private int value;
    private int index;
    private Node right;
    private Node left;

    public Node(int val) {
        value = val;
    }

    public Node(int val, int ind) {
        value = val;
        index = ind;
    }
}
