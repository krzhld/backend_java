package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    static long minutesToSeconds(String str) {
        String[] time = str.split(":");
        long seconds = Long.parseLong(time[1]);
        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }
        seconds += Long.parseLong(time[0]) * SECONDS_IN_MINUTE;
        return seconds;
    }
}
