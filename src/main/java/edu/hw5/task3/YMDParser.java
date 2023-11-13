package edu.hw5.task3;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YMDParser extends Parser {

    private static final Pattern DATE_PATTERN =
        Pattern.compile("^(\\d{3,4})-([1-9]|1[0-2])-([1-9]|[12]\\d|3[01])$");

    @SuppressWarnings("MagicNumber")
    @Override
    public LocalDate parse(String date) {
        Matcher matcher = DATE_PATTERN.matcher(date);
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            return LocalDate.of(year, month, day);
        } else {
            return next.parse(date);
        }
    }
}
