package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    }

}
