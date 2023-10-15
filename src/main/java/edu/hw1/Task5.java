package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public final class Task5 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    private static final char ASCII_SHIFT = '0';
    private static final int TEN = 10;

    private static int[] intToArray(long number) {
        String str = Long.toString(number);
        int[] resultArray = new int[str.length()];
        for (int i = 0; i < str.length(); ++i) {
            resultArray[i] = str.charAt(i) - ASCII_SHIFT;
        }
        return resultArray;
    }

    private static int arrayToInt(int[] arrNumber) {
        int resultNumber = 0;
        for (int i = 0; i < arrNumber.length; i++) {
            resultNumber = resultNumber * TEN + arrNumber[i];
        }
        return resultNumber;
    }

    private static boolean isPalindrome(long number) {
        int[] arrNumber = intToArray(number);
        int leftIndex = 0;
        int rightIndex = arrNumber.length - 1;

        while (leftIndex < rightIndex) {
            if (arrNumber[leftIndex] != arrNumber[rightIndex]) {
                return false;
            }
            ++leftIndex;
            --rightIndex;
        }
        return true;
    }

    static boolean isPalindromeDescendant(long number) {
        if (number <= TEN) {
            return false;
        }
        if (isPalindrome(number)) {
            return true;
        }
        int[] arrNumber = intToArray(number);
        int n = arrNumber.length;

        int[] newArrNumber = new int[n / 2 + n % 2];
        for (int i = 0; i < newArrNumber.length; ++i) {
            newArrNumber[i] = arrNumber[2 * i] + arrNumber[2 * i + 1];
        }
        String str = Arrays.stream(newArrNumber)
            .mapToObj(String::valueOf)
            .reduce((x, y) -> x + "" + y)
            .get();

        return isPalindromeDescendant(Long.parseLong(str));
    }
}
