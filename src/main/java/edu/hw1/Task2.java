package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private final static int TEN = 10;

    private Task2() {
    }

    static int countDigits(long number) {
        long curNumber = number;
        if (curNumber == 0) {
            return 1;
        }
        int digits = 0;
        while (curNumber != 0) {
            curNumber /= TEN;
            ++digits;
        }
        return digits;
    }
}
