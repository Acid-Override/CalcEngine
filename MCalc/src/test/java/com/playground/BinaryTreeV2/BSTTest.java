package com.playground.BinaryTreeV2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BSTTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        BST test = new BST();
        test.add(10);
        test.add(20);
        test.add(5);

        log.info("BSTv2 = {}", test.getRootNode());
    }

    @Test
    void find() {
        BST root = new BST();
        root.add(10);
        root.add(15);
        root.add(13);
        root.add(18);
        root.add(5);
        root.add(7);
        root.add(2);

        BST.Node node = root.find(2);
        log.info("NODE FOUND={}", node);
        assertEquals(2, node.value);
    }
}