package com.DailyByte.CharacterUpdate;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s and an integer k, return the length of the longest substring in s you can create that contains a single capital letter.
 * You may apply k operations to s where a single operation consists of selecting one capital letter and modifying it to be another capital letter.
 * Note: s will only contain uppercase alphabetical characters.
 *
 * Ex: Given the following s and k…
 *
 * s = "BBAA", k = 2, return 4 (both B's can be changed to A's or both A's can be changed to B's).
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CharacterUpdate {

    private String s;
    private int k;

    public int charUp() {
        Map<Character, Integer> hm = new HashMap<>();
        for (char c : s.toCharArray()) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
//            hm.merge(c, 1, Integer::sum);
        }
        hm.forEach((k, v) -> System.out.println(k + " " + v));

        return 0;
    }




    /**
     * Solution
     * https://thedailybyte.dev/solution/347?token=a881f840410746845ef6c6eda70c4f16628af1f4f7871c7157fbb98a5629469c
     *
     * Given a string s and an integer k, return the length of the longest substring in s you can create that contains a single capital letter.
     * You may apply k operations to s where a single operation consists of selecting one capital letter and modifying it to be another capital letter.
     * Note: s will only contain uppercase alphabetical characters.
     *
     * Ex: Given the following s and k…
     *
     * s = "BBAA", k = 2, return 4 (both B's can be changed to A's or both A's can be changed to B's).
     *
     * To solve this problem, we can use a sliding window approach. We can have two pointers, left and right which will represent the indices of the
     * longest substring we can create that contains a single capital letter. While creating this window, we can count the number of each character we
     * traverse using a counts array (i.e. each index in the array represents the count of the corresponding character). At every iteration of our
     * loop, we can extend our right pointer. Then, we can update a max variable to hold the count of the most frequently occurring character thus far.
     * Now, we can do simple arithmetic to determine if the current size of our window (i.e. right - left + 1) can be transformed, using our k operations,
     * to only contain a single capital letter. This can be answered by subtracting our max from our window size (i.e. right - left + 1) and seeing if the
     * result is larger than k. If it is, we can update our left pointer until we’ve done at most k operations (i.e. the characters that don’t match in our
     * substring can be updated using our k operations). Once we’ve updated our window size to represent a valid set of characters, we can update a longest
     * variable to store our result. Once our loop ends, we can simply return our longest.
     *
     *
     */
    public int characterUpdate(String s, int k) {
        int[] counts = new int[26];
        int left = 0;
        int max = 0;
        int longest = 0;
        for (int right = 0; right < s.length(); right++) {
            max = Math.max(max, ++counts[s.charAt(right) - 'A']);
            while (right - left + 1 - max > k) {
                counts[s.charAt(left++) - 'A']--;
            }
            longest = Math.max(longest, right - left + 1);
        }

        return longest;
    }
}
