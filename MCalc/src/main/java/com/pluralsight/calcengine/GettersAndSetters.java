package com.pluralsight.calcengine;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GettersAndSetters {

//    @Test
//    public void firstTest(){
//        GettersAndSetters gettersAndSetters = new GettersAndSetters();
//        GettersAndSetters.Node root = gettersAndSetters.new Node(10);
//        root.left = new Node(20);
//        root.right = new Node(30);
//        log.info(String.valueOf(root.value + root.right.value + root.left.value));
//    }

    public static void main (String... args) {
//        GettersAndSetters gettersAndSetters = new GettersAndSetters();
        GettersAndSetters.Node root = new Node(10);
        log.info("Value:{}", root.getValue());

    }
    //create a new root node





    @Data
    private static class Node {
        private int value;
        private Node left, right;

        public Node(int value) {
            this.value = value;
        }

    }
}
