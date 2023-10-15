package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private static final Logger LOGGER = LogManager.getLogger();
    static final int SECONDS_IN_MINUTE = 60;
    static final int NO_COLON = -2;
    static final int INCORRECT_INPUT = -3;

    private Task1() {
    }

    static long minutesToSeconds(String str) {
        if (!str.contains(":")) {
            return NO_COLON;
        }
        String[] time = str.split(":");
        long seconds;
        long minutes;
        try {
            seconds = Long.parseLong(time[1]);
            minutes = Long.parseLong(time[0]);
        } catch (NumberFormatException e) {
            return INCORRECT_INPUT;
        }
        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }
        return minutes * SECONDS_IN_MINUTE + seconds;
    }
}
