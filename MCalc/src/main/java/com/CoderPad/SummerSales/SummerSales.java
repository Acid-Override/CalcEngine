package com.CoderPad.SummerSales;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * It's almost the Summer Sales!
 *
 * You work for a shop that wishes to give a discount of discount% to the most expensive item purchased by a given
 * customer during the sales period. Only one product can benefit from the discount.
 *
 * You are tasked by the shop owner to implement the method calculateTotalPrice(prices, discount) which takes the
 * list of prices of the products purchased by a customer and the percentage discount as parameters and returns
 * the total purchase price as an integer (rounded down if the total is a float number).
 *
 * Constraints:
 *
 *     0 ≤ discount ≤ 100
 *     0 < price of a product < 100000
 *     0 < number of products < 100
 */
@Slf4j
public class SummerSales {

    public static int calculateTotalPrice(int[] prices, int discount) {
        // iterate over the prices
          // sum each price
          // if current > max
            // set as max
        // handle discount with total, max, discount
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;

        for (int i = 0; i < prices.length; i++) {
            total = total.add(BigDecimal.valueOf(prices[i]));
            if (max.compareTo(BigDecimal.valueOf(prices[i])) < 0) {
                max = BigDecimal.valueOf(prices[i]);
            }
        }
        return processDiscount(total, max, discount);


    }

    private static int processDiscount(BigDecimal total, BigDecimal max, int discount) {
        // subtract max from total
        // calculateDiscount(max, discount)
        // add discounted max to total
        // return total

        total = total.subtract(max);
        BigDecimal maxCostWithDiscount = calculateDiscount(max, discount);
        log.info("Discount on most expensive item={}", maxCostWithDiscount);
        return total.add(maxCostWithDiscount).intValueExact();
    }

    private static BigDecimal calculateDiscount(BigDecimal max, int discount) {
        log.info("max={}", max);
        log.info("discount={}", discount);
        BigDecimal convertedDiscount = BigDecimal.valueOf(1)
                .subtract(BigDecimal.valueOf(discount).movePointLeft(2)
                );
       return  max.multiply(convertedDiscount)
               .setScale(0, RoundingMode.FLOOR);
    }
}
