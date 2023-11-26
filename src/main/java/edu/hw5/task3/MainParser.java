package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class MainParser {

    private MainParser() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        Parser dayAgoParser = new DayAgoParser();
        Parser dmyParser = new DMYParser();
        Parser todayParser = new TodayParser();
        Parser tomorrowParser = new TomorrowParser();
        Parser yesterdayParser = new YesterdayParser();
        Parser ymdParser = new YMDParser();

        dayAgoParser.setNext(dmyParser);
        dmyParser.setNext(todayParser);
        todayParser.setNext(tomorrowParser);
        tomorrowParser.setNext(yesterdayParser);
        yesterdayParser.setNext(ymdParser);

        LocalDate date = dayAgoParser.parse(string);
        if (date != null) {
            return Optional.of(date);
        } else {
            return Optional.empty();
        }
    }
}
