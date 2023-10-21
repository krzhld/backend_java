package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task4() {
    }

    static String fixString(String brokenStr) {
        char[] arrChar = brokenStr.toCharArray();

        char temp;
        int iterations = brokenStr.length() / 2;
        for (int i = 0; i < iterations; ++i) {
            temp = arrChar[2 * i];
            arrChar[2 * i] = arrChar[2 * i + 1];
            arrChar[2 * i + 1] = temp;
        }

        return new String(arrChar);
    }
}
