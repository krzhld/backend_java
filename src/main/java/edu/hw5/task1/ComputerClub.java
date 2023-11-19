package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComputerClub {
    private ComputerClub() {
    }

    public static TimeHoursMinutes countAverageTimeSpent(String[] durations) {
        Duration duration = Duration.ZERO;
        for (String curDuration : durations) {
            String[] splitTime = curDuration.split(" - ");

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

            LocalDateTime formattedTimeFrom = LocalDateTime.parse(splitTime[0], dateTimeFormatter);
            LocalDateTime formattedTimeTo = LocalDateTime.parse(splitTime[1], dateTimeFormatter);

            duration = duration.plus(Duration.between(formattedTimeFrom, formattedTimeTo));
        }
        int numberVisitors = durations.length;
        duration = duration.dividedBy(numberVisitors);

        long hours = duration.toHours();
        long minutes = duration.minusHours(duration.toHours()).toMinutes();
        return new TimeHoursMinutes(hours, minutes);
    }
}
