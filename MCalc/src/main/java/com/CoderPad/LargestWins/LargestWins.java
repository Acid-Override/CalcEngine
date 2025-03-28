package com.CoderPad.LargestWins;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 *
 Implement  the method findLargest(numbers), so it returns the largest number from the list numbers.

 Notes:

 numbers contains only integers.
 numbers always contains at least one element, and never contains more than 10 elements.
 The integers are ranged from -2^31 to +2^31 - 1 (they always fit in a 32-bit signed integer).


 Implementation
 Function
 Implement the method findLargest.
 Parameters
 numbers (List<Integer>): Some integer numbers.
 Return value
 largest (int): The largest value among the numbers given in parameters.
 Constraints
 1 <= length(numbers) <= 10
 -2,147,483,648 <= numbers[] <= 2,147,483,647
 Available RAM: 512MB
 Timeout: 2 seconds
 */
@Slf4j
public class LargestWins {

    public int findLargest(List<Integer> numbers) {
        return Collections.max(numbers);
    }
}
