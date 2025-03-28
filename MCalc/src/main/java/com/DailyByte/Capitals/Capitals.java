package com.DailyByte.Capitals;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Capitals {

    private static final HashMap<String, Integer> map = new HashMap<>();
    private static final HashMap<String, Integer> error = new HashMap<>();

    public Map<String, Integer> sortCapitals(String[] capitals) {
        Arrays.stream(capitals).forEach(cap -> {
            if (verifyLetters(cap)) {
                map.put(cap, map.getOrDefault(cap, 0) + 1);
            } else {
                error.put(cap, error.getOrDefault(cap, 0) + 1);
            }
        });
        return map;
    }

    private boolean verifyLetters(String k) {
        return Arrays.stream(k.split(" "))
                .map(String::trim)
                .map(word -> word.matches("\\b([A-Z]\\w*)\\b"))
                .reduce(true, Boolean::logicalAnd);
    }

    public static Map<String, Integer> getError() { return error; }

    public static Map<String, Integer> getMap() { return map; }
}
