package com.playground.MoveZeroToEnd;

public class MoveZeroToEnd {

    /**
     * Moves all zeros to the end of the given array, keeping the relative order
     * of all other elements the same.
     *
     * @param array the given array
     * @return the modified array
     */
    public int[] moveZeroToEnd(int[] array) {

        // create current index counter
        // iterate over array
            // if value is number > 0
                // set to current index counter
                // increment counter
            // if value is 0
                // continue
        // from counter to end of array
            // set zeros

        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                array[counter] = array[i];
                counter++;
            } else if (array[i] == 0) {
                continue;
            }
        }

        for (int i = counter; i < array.length; i++) {
            array[i] = 0;
        }

        return array;
    }
}
