package com.CoderPad.SummerSales;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SummerSalesTest {

    @Test
    void calculateTotalPrice() {
        int[] prices = {1, 2, 3, 4};
        assertEquals(8, SummerSales.calculateTotalPrice(prices, 50));
    }
}