package com.DailyByte.EvenVowel;


import lombok.*;

import java.util.HashMap;
import java.util.Map;

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

    //DailyByte Solution
    public int evenVowelSubstring(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        counts.put('a', 0);
        counts.put('e', 0);
        counts.put('i', 0);
        counts.put('o', 0);
        counts.put('u', 0);

        int left = 0;
        int max = 0;
        for(int right = 0; right < s.length(); right++) {
            counts.put(s.charAt(right), counts.getOrDefault(s.charAt(right), 0) + 1);

            Map<Character, Integer> temp = new HashMap<>();
            temp.put('a', counts.get('a'));
            temp.put('e', counts.get('e'));
            temp.put('i', counts.get('i'));
            temp.put('o', counts.get('o'));
            temp.put('u', counts.get('u'));

            while(temp.values().stream().filter(i -> i%2==1).count() > 0) {
                temp.put(s.charAt(left), temp.getOrDefault(s.charAt(left), 1) - 1);
                left++;
            }

            max = Math.max(max, right - left + 1);
            left = 0;
        }
        return max;
    }
}
