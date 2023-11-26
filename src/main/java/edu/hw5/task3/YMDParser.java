package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class YMDParser extends Parser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-M-d");

    @Override
    public LocalDate parse(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
            return LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
        } catch (DateTimeParseException e) {
            return next.parse(date);
        }
    }
}
