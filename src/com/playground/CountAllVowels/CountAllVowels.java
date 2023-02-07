package com.playground.CountAllVowels;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CountAllVowels {

    private String vowels = "aeiouy";

    public Integer countAllVowels (String letters) {

        return letters.toLowerCase(Locale.ROOT).chars().mapToObj(x -> (char) x).filter(m -> vowels.contains(m.toString())).mapToInt(e -> 1).sum();
    }

}
