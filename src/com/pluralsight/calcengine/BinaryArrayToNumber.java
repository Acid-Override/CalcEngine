package com.pluralsight.calcengine;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class BinaryArrayToNumber {

    public static void main(String[] args) {
        System.out.println(convertBinaryArrayToNumber(new ArrayList<>( Arrays.asList(0, 1, 1, 0) ) )); //11
        System.out.println(convertBinaryArrayToInt(new int[]{1, 0, 0, 1}));
        System.out.println(convertBinaryArrayToIntStreamAndReduce(Arrays.asList(0, 1, 1, 0) ));
    }


    public static int convertBinaryArrayToNumber ( List<Integer> arr ){
        String[] strArray = new String[arr.size()];

        for ( int i = 0; i < arr.size(); i++ ) {
            strArray[i] = String.valueOf(arr.get(i));
        }
        String strJoin = String.join("", strArray);

        return Integer.parseInt(strJoin, 2);
    }

    public static int convertBinaryArrayToInt ( final int [] nums ) {
        final StringBuffer sb = new StringBuffer();
        for ( final int x : nums) {
            sb.append(x);
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    public static int convertBinaryArrayToIntStreamAndReduce(List<Integer> binary) {
        return binary.stream().reduce((x, y) -> x * 2 + y).get();

    }


}
