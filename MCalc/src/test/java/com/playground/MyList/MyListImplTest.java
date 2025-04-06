package com.playground.MyList;

import com.playground.MyList.MyListV2.MyListV2Impl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyListImplTest {

    private static MyListV2Impl<Integer> list;

    @BeforeEach
    void init() {
        list = new MyListV2Impl<>();
    }

    @Test
    void add() {
        assertNotNull(list, "List should not be null");

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        assertEquals(4, list.size(), "List size should be 4 after adding 4 elements");
        assertTrue(list.contains(10), "List should contain 10");
        assertTrue(list.contains(20), "List should contain 20");
        assertTrue(list.contains(30), "List should contain 30");
        assertTrue(list.contains(40), "List should contain 40");
    }

    @Test
    void addAll() {
        assertNotNull(list, "List should not be null");

        List<Integer> list2 = new ArrayList<>();
        list2.add(10);
        list2.add(20);
        list2.add(30);
        list2.add(40);

        list.addAll(list2);

        assertEquals(4, list.size(), "List size should be 4 after adding 4 elements");
        assertTrue(list.contains(10), "List should contain 10");
        assertTrue(list.contains(20), "List should contain 20");
        assertTrue(list.contains(30), "List should contain 30");
        assertTrue(list.contains(40), "List should contain 40");

    }

    @Test
    void getList() {
        assertNotNull(list, "List should not be null");

        assertEquals(0, list.size(), "List size should be 0");
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        assertEquals(4, list.size(), "List size should be 4 after adding 4 elements");
    }

    @Test
    void contains() {
        assertNotNull(list, "List should not be null");

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        assertTrue(list.contains(10), "List should contain 10");
        assertTrue(list.contains(20), "List should contain 20");
        assertTrue(list.contains(30), "List should contain 30");
        assertTrue(list.contains(40), "List should contain 40");
    }
}