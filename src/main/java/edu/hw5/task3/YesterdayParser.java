package edu.hw5.task3;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YesterdayParser extends Parser {

    private static final Pattern DATE_PATTERN = Pattern.compile("yesterday");

    @Override
    public LocalDate parse(String date) {
        Matcher matcher = DATE_PATTERN.matcher(date);
        if (matcher.find()) {
            return LocalDate.now().minusDays(1);
        } else {
            return next.parse(date);
        }
    }
}
