package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    final private static char ASCII_SHIFT = '0';
    final private static int TEN = 10;

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
        if (number <= 10) {
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
        return isPalindromeDescendant(arrayToInt(newArrNumber));
    }

    public static void main(String[] args) {
    }
}
