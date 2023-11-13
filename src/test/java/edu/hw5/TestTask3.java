package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw5.task3.MainParser.parseDate;

public class TestTask3 {
    public static Arguments[] parseDateParams() {
        return new Arguments[] {
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1)))
        };
    }

    @ParameterizedTest
    @MethodSource("parseDateParams")
    void testParseDate(String str, Optional<LocalDate> expectedResult) {
        Optional<LocalDate> result = parseDate(str);

        assertThat(result).isEqualTo(expectedResult);
    }

}
