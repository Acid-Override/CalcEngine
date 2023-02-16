package com.DailyByte.EvenVowel;


import lombok.*;

import java.util.HashMap;

/**
 * Given a string, s, return the length of the longest substring that contains every vowel occurring an even number of times.
 * Note: You may assume s only contains lowercase alphabetical characters and the vowels you must account for are a, e, i, o, and u.
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class EvenVowel {

    private String input;
    final private HashMap<Character, Integer> vowelMap = new HashMap<>();
    private String vowels;

    public int evaluateEvenVowelString() {

        buildHashMap();

        for ( int i = 0; i < input.length(); i++) {
            if(vowelMap.containsKey(input.charAt(i))) {
                vowelMap.put(input.charAt(i), vowelMap.get(input.charAt(i)) + 1 );
            }
        }
        vowelMap.values().removeIf(v -> v % 2 == 0);
        return input.length() - vowelMap.size();

    }

    private void buildHashMap() {
        for(int i = 0; i < vowels.length(); i++ ) {
            vowelMap.put(vowels.charAt(i), 0);
        }
    }


}
