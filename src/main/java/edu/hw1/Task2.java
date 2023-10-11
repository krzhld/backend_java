package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task2() {
    }

    static int countDigits(long number) {
        if (number == 0) {
            return 1;
        }
        int digits = 0;
        while (number != 0) {
            number /= 10;
            ++digits;
        }
        return digits;
    }

    public static void main(String[] args) {
        LOGGER.info(countDigits(4666));
        LOGGER.info(countDigits(544));
        LOGGER.info(countDigits(0));
    }
}
