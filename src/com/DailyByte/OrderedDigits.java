package com.DailyByte;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/*
* Given two values, low and high, return all the values (inclusive) that contain
* consecutive digits (ordered)
*
* input : low and high integers
* output: List of Integers
 */

@Getter @Setter
@NoArgsConstructor
public class OrderedDigits extends Object {

    private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Integer low;
    private Integer high;
    private List<Integer> result = new ArrayList<>();

    public OrderedDigits(Integer low, Integer high) {
        this.low = low;
        this.high = high;
    }

    public List<Integer> orderedDigits () {
        for (int i = low; i <= high; i++) {
            if(consecutive(i))
                result.add(i);
        }
        log.info(result.toString());
        return result;
    }

    private boolean consecutive(Integer num) {
        if(num < 10)
            return false;
        String numString = num.toString();
        for ( int i = 0; i < numString.length() - 1; i++){
            if(numString.charAt(i+1) - numString.charAt(i) != 1){
                return false;
            }
        }
        return true;
    }

}
