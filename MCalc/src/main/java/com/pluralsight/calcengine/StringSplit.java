package com.pluralsight.calcengine;

import lombok.extern.slf4j.Slf4j;


import java.util.Arrays;
import java.util.List;

@Slf4j
public class StringSplit {

    public static void main(String[] args) {
        String[] result = solution("abcdefg");
        for (String item : result) {
            log.info(item);
        }
        log.info(Arrays.toString(result));

    }

    public static String[] solution(String s) {
       if ( s.length()== 0 ) {
           return new String[]{};
       }
       if ( s.length() % 2 != 0 ) {
           s += "_";
       }

       int spaceNeeded = s.length()/2;
       String[] accArr = new String[spaceNeeded];

       // array counter variable to 'push' pairs in
       int j = 0;


       for ( int i = 0; i < s.length(); i+=2 ) {
           StringBuilder sb = new StringBuilder();
           sb.append(s.charAt(i));
           sb.append(s.charAt(i + 1));
           accArr[j] = sb.toString();
           j++;
       }
       return accArr;

    }
}
