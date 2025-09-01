package com.DailyByte.TreeReconstruction;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TreeReconstructionTest {

    private TreeReconstruction tr;

    @BeforeEach
    void setUp() {
        tr = new TreeReconstruction();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void treeReconInit() {
        tr.setPreorder(new int[] {1, 2, 3, 4, 5, 6, 7});
        tr.setInorder(new int[] {2, 1, 3});
        tr.treeRecon();
        log.info(tr.toString());
    }
    @Test
    void addANode() {
        tr.add(10);
        tr.add(20);
        tr.add(5);
        tr.add(7);
        tr.add(1);
        tr.add(15);
        tr.add(25);
        log.info(tr.toString());
    }
}