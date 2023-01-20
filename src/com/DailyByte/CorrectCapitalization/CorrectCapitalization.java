package com.DailyByte.CorrectCapitalization;

import java.util.Objects;

/**
 * This question is asked by Google. Given a string, return whether or not it uses capitalization correctly.
 * A string correctly uses capitalization if all letters are capitalized, no letters are capitalized,
 * or only the first letter is capitalized.
 *
 * Ex: Given the following strings...
 *
 * "USA", return true
 * "Calvin", return true
 * "compUter", return false
 * "coding", return true
 */
public class CorrectCapitalization {

    public boolean correctCapitalization(String word) {
        boolean result = false;
        System.out.println(word);
        if (Objects.equals(word.toUpperCase(), word)) {
            result = true;
        }
        if(word.substring(1).toLowerCase().equals(word.substring(1))) {
            if(word.substring(0, 0).toUpperCase().equals(word.substring(0, 0))){
                result = true;
            }
        }
        if(word.substring(0).toLowerCase().equals(word.substring(0))){
            result = true;
        }
        System.out.println(result);
        return result;
    }

}
