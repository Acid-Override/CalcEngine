package com.playground.PhoneNumber;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumber {

    private static final Map<Integer, List<String>> dictionary = new HashMap<>();

    {
        dictionary.put(2, List.of("a", "b", "c"));
        dictionary.put(3, List.of("d", "e", "f"));
        dictionary.put(4, List.of("g", "h", "i"));
        dictionary.put(5, List.of("j", "k", "l"));
        dictionary.put(6, List.of("m", "n", "o"));
        dictionary.put(7, List.of("p", "q", "r", "s"));
        dictionary.put(8, List.of("t", "u", "v"));
        dictionary.put(9, List.of("w", "x", "y", "z"));
    }

    public String convertPhoneNumber(String phoneNumber) {
        System.out.println("Phone number: " + phoneNumber);
        validatePhoneNumber(phoneNumber);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < phoneNumber.length(); i++) {
            String currentChar = phoneNumber.charAt(i) + "";
                if (Character.isDigit(currentChar.charAt(0))) {
                    sb.append(currentChar);
                    continue;
                }

                dictionary.forEach((key, value) -> {
                    if (value.contains(currentChar.toLowerCase())) {
                        sb.append(key);
                    }
                });
        }

        System.out.println("Letters: " + sb);
        return sb.toString();
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11 || phoneNumber.charAt(0) != '1') {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

}
