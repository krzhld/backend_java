package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class SearcherUnhappyFridays {
    private SearcherUnhappyFridays() {

    }

    private static final int FRIDAY = 5;
    private static final int UNHAPPY_DAY = 13;

    public static List<LocalDate> getAllUnhappyFridaysOfYear(int year) {
        List<LocalDate> unhappyFridays = new ArrayList<>();
        LocalDate curDate = LocalDate.of(year, 1, UNHAPPY_DAY);
        while (curDate.getYear() == year) {
            if (curDate.getDayOfWeek().getValue() == FRIDAY) {
                unhappyFridays.add(curDate);
            }
            curDate = curDate.plusMonths(1);
        }
        return unhappyFridays;
    }

    @SuppressWarnings("ParameterAssignment")
    public static LocalDate getNearestUnhappyFriday(LocalDate date) {
        return date.with(
            TemporalAdjusters.ofDateAdjuster(
                v -> {
                    v = v.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                    while (v.getDayOfMonth() != UNHAPPY_DAY) {
                        v = v.plusWeeks(1);
                    }
                    return v;
                }
            ));
    }
}
