package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {

    }

    static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 | a2.length == 0) {
            return false;
        }

        int[] a1Sorted = a1.clone();
        int[] a2Sorted = a2.clone();

        Arrays.sort(a1Sorted);
        Arrays.sort(a2Sorted);

        return (a1Sorted[0] > a2Sorted[0]) & (a1Sorted[a1Sorted.length - 1] < a2Sorted[a2Sorted.length - 1]);
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4};
        int[] b1 = {0, 6};
        LOGGER.info(isNestable(a1, b1));

        int[] a2 = {3, 1};
        int[] b2 = {4, 0};
        LOGGER.info(isNestable(a2, b2));

        int[] a3 = {9, 9, 8};
        int[] b3 = {8, 9};
        LOGGER.info(isNestable(a3, b3));

        int[] a4 = {1, 2, 3, 4};
        int[] b4 = {2, 3};
        LOGGER.info(isNestable(a4, b4));
    }

}
