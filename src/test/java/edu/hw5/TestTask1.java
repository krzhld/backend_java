package edu.hw5;

import edu.hw5.task1.TimeHoursMinutes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.task1.ComputerClub.countAverageTimeSpent;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {
    private static Arguments[] averageTimeSpentParameters() {
        return new Arguments[] {
            Arguments.of(
                new String[] {"2020-01-12, 18:20 - 2020-01-12, 21:50", "2020-01-29, 22:30 - 2020-01-30, 02:20"},
                new TimeHoursMinutes(3, 40)
            ),
            Arguments.of(
                new String[] {"2010-02-12, 21:20 - 2010-02-13, 21:20", "2010-04-01, 12:00 - 2010-04-02, 12:00"},
                new TimeHoursMinutes(24, 0)
            ),
            Arguments.of(
                new String[] {"2000-06-10, 13:30 - 2000-06-10, 13:40", "2000-07-11, 08:15 - 2000-07-11, 08:45"},
                new TimeHoursMinutes(0, 20)
            )
        };
    }

    @ParameterizedTest
    @MethodSource("averageTimeSpentParameters")
    void testAverageTimeSpend(String[] durations, TimeHoursMinutes expectedResult) {
        TimeHoursMinutes timeSpend = countAverageTimeSpent(durations);

        assertThat(timeSpend.hours()).isEqualTo(expectedResult.hours());
        assertThat(timeSpend.minutes()).isEqualTo(expectedResult.minutes());
    }

}
