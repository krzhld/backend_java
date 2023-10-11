package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    final private static char ASCII_SHIFT = '0';

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
            resultNumber = resultNumber * 10 + arrNumber[i];
        }
        return resultNumber;
    }

    private static boolean isPalindrome(long number) {
        int[] arr_number = intToArray(number);
        int leftIndex = 0;
        int rightIndex = arr_number.length - 1;

        while (leftIndex < rightIndex) {
            if (arr_number[leftIndex] != arr_number[rightIndex]) {
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
        LOGGER.info(isPalindromeDescendant(11211230)); // 11211230 -> 2333 -> 56 -> 11
        LOGGER.info(isPalindromeDescendant(13001120)); // 13001120 -> 4022 ➞ 44
        LOGGER.info(isPalindromeDescendant(23336014)); // 23336014 -> 5665
        LOGGER.info(isPalindromeDescendant(11));
        LOGGER.info(isPalindromeDescendant(10));
        LOGGER.info(isPalindromeDescendant(23));
        LOGGER.info(isPalindromeDescendant(1112));
    }
}
