package edu.hw3;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumerals {
    private RomanNumerals() {
    }

    private static Map<Integer, String> NUMERALS_ROMAN = null;
    private static final int MAX_VALUE = 4000;

    private static void initNumeralsRoman() {
        if (NUMERALS_ROMAN != null) {
            return;
        }
        Map<Integer, String> numerals = new LinkedHashMap<>();
        numerals.put(1000, "M");
        numerals.put(900, "CM");
        numerals.put(500, "D");
        numerals.put(400, "CD");
        numerals.put(100, "C");
        numerals.put(90, "XC");
        numerals.put(50, "L");
        numerals.put(40, "XL");
        numerals.put(10, "X");
        numerals.put(9, "IX");
        numerals.put(5, "V");
        numerals.put(4, "IV");
        numerals.put(1, "I");

        NUMERALS_ROMAN = Collections.unmodifiableMap(numerals);
    }

    public static String convertToRoman(int num) {
        if (num >= MAX_VALUE || num <= 0) {
            throw new IllegalStateException("Incorrect input number!");
        }
        initNumeralsRoman();
        StringBuilder answer = new StringBuilder();
        while (num > 0) {
            for (Integer key : NUMERALS_ROMAN.keySet()) {
                while (num >= key) {
                    answer.append(NUMERALS_ROMAN.get(key));
                    num -= key;
                }
            }
        }
        return answer.toString();
    }
}
