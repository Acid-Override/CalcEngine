package com.DailyByte.WordWithInWord;


import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words, return all strings in words that are a substring of another word.
 * Note: The order in which you return the substrings does not matter.
 *
 * Ex: Given the following words…
 *
 * words = ["abc", "a", "b"], return ["a", "b"].
 * Ex: Given the following words…
 *
 * words = ["ab", "ba", "c"], return [].
 *
 * Solution:
 * https://thedailybyte.dev/solution/308?token=7152537fa42e26948f1750418a0d79461fd3a7d0e01cd3449ce4109ecf76bec2
 */
public class WordWithinWord {

    public String[] wordWithinWord(String[] arr) {

        List<String> result = new ArrayList<>();
        for(String word: arr) {
            if (isSubString(arr, word))
                result.add(word);
        }
       return result.toArray(String[]::new);
    }


    private boolean isSubString(String[] arr, String subString) {
        boolean result = false;
        for (String word: arr){
            if (word.indexOf(subString) > 0 && !word.equals(subString))
                result = true;
        }
        return result;
    }


}
