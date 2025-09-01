package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HowManyNumbers {

    public static void main(String... args) {
        List<Long> result = findAll(10, 3);
        log.info("How Many Numbers Result:{}", result);

    }

        public static long count(int sumDigits, int numDigits, int min) {
            if (sumDigits == numDigits * 9) return 1;
            if (sumDigits == numDigits * min) return 1;
            if (sumDigits > numDigits * 9) return 0;
            int max = sumDigits / numDigits;
            long total = 0;
            for (int i=min; i<=max; i++) {
                total += count(sumDigits - i, numDigits - 1, i);
            }
            return total;
        }
        public static List<Long> findAll(final int sumDigits, final int numDigits) {
            if (sumDigits / numDigits == 0 || sumDigits > numDigits * 9) return new ArrayList<Long>();
            long max = 0;
            for (int i=numDigits, sum=sumDigits; i>0; i--) {
                max *= 10;
                max += sum / i;
                sum -= sum / i;
            }
            long min = 0;
            for (int i=numDigits, sum=sumDigits; i>0; i--) {
                min *= 10;
                int m = sum > (i-1)*9 ? sum - (i-1)*9 : 1;
                min += m;
                sum -= m;
            }


            return java.util.Arrays.asList(count(sumDigits, numDigits, 1), min, max);
        }


}
