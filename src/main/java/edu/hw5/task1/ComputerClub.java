package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;

public class ComputerClub {

    private static final int SECONDS_IN_MINUTE = 60;

    public static TimeHoursMinutes countAverageTimeSpent(String[] durations) {
        long totalMinutes = 0;
        for (String curDuration : durations) {
            String[] splitTime = curDuration.split(" - ");
            LocalDateTime formattedTimeFrom = parseStringToDateTime(splitTime[0]);
            LocalDateTime formattedTimeTo = parseStringToDateTime(splitTime[1]);
            totalMinutes += Duration.between(formattedTimeFrom, formattedTimeTo).toMinutes();
        }
        int numberVisitors = durations.length;
        var averageTotalMinutes = totalMinutes / numberVisitors;
        return new TimeHoursMinutes(
            (int) averageTotalMinutes / SECONDS_IN_MINUTE,
            (int) averageTotalMinutes % SECONDS_IN_MINUTE
        );
    }

    private static LocalDateTime parseStringToDateTime(String str) {
        String formattedStr = str.replace(", ", "T") + ":00";
        return LocalDateTime.parse(formattedStr);
    }
}
