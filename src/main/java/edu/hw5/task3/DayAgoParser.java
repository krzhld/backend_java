package edu.hw5.task3;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayAgoParser extends Parser {

    private static final Pattern DATE_PATTERN = Pattern.compile("([1-9]\\d*) days? ago");

    @Override
    public LocalDate parse(String date) {
        Matcher matcher = DATE_PATTERN.matcher(date);
        if (matcher.find()) {
            int daysAgo = Integer.parseInt(matcher.group(1));
            return LocalDate.now().minusDays(daysAgo);
        } else {
            return next.parse(date);
        }
    }
}
