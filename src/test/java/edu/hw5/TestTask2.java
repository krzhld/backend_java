package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw5.task2.SearcherUnhappyFridays.getAllUnhappyFridaysOfYear;
import static edu.hw5.task2.SearcherUnhappyFridays.getNearestUnhappyFriday;

public class TestTask2 {
    public static Arguments[] allUnhappyFridaysOfYearParameters() {
        return new Arguments[] {
            Arguments.of(
                1925,
                List.of(
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                )
            ),
            Arguments.of(
                1990,
                List.of(
                    LocalDate.of(1990, 4, 13),
                    LocalDate.of(1990, 7, 13)
                )
            ),
            Arguments.of(
                2022,
                List.of(LocalDate.of(2022, 5, 13))
            )
        };
    }

    public static Arguments[] nearestUnhappyFridaysParameters() {
        return new Arguments[] {
            Arguments.of(LocalDate.of(1925, 3, 10), LocalDate.of(1925, 3, 13)),
            Arguments.of(LocalDate.of(1990, 4, 9), LocalDate.of(1990, 4, 13)),
            Arguments.of(LocalDate.of(2009, 2, 1), LocalDate.of(2009, 2, 13)),
        };
    }

    @ParameterizedTest
    @MethodSource("allUnhappyFridaysOfYearParameters")
    void testGetAllFriday13thOfYear(int year, List<LocalDate> expectedResult) {
        List<LocalDate> fridays = getAllUnhappyFridaysOfYear(year);

        assertThat(fridays).containsExactlyElementsOf(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("nearestUnhappyFridaysParameters")
    void testNextFriday13th(LocalDate date, LocalDate expectedResult) {
        LocalDate friday = getNearestUnhappyFriday(date);

        assertThat(friday).isEqualTo(expectedResult);
    }
}
