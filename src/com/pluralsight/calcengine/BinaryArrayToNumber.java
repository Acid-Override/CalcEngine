package com.pluralsight.calcengine;

//import java.util.Arrays;

public class BinaryArrayToNumber {

    public static void main(String[] args) {
        System.out.println(convertBinaryArrayToNumber(new int[]{1, 0, 0, 1})); //11
        System.out.println(convertBinaryArrayToInt(new int[]{1, 0, 0, 1}));
    }


    public static int convertBinaryArrayToNumber ( int[] arr ){
        String[] strArray = new String[arr.length];

        for ( int i = 0; i < arr.length; i++ ) {
            strArray[i] = String.valueOf(arr[i]);
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

}
