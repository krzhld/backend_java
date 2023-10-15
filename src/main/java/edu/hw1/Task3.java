package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task3 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task3() {

    }

    static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 | a2.length == 0) {
            return false;
        }

        int max1 = Arrays.stream(a1).max().getAsInt();
        int min1 = Arrays.stream(a1).min().getAsInt();

        int max2 = Arrays.stream(a2).max().getAsInt();
        int min2 = Arrays.stream(a2).min().getAsInt();

        return (min1 > min2) & (max1 < max2);
    }

}
