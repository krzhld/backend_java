package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import static edu.hw5.task4.CheckerPassword.isPasswordCorrect;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw5.task3.MainParser.parseDate;

public class TestTask4 {
    public static Arguments[] passwordCheckerParameters() {
        return new Arguments[] {
            Arguments.of("pass", false),
            Arguments.of("very_weak", false),
            Arguments.of("@!!!", true),
            Arguments.of("scoofy_scoof!", true),
            Arguments.of("uwuwuwuwu%&!", true)
        };
    }

    @ParameterizedTest
    @MethodSource("passwordCheckerParameters")
    void testPasswordChecker(String password, boolean expectedResult) {
        boolean result = isPasswordCorrect(password);

        assertThat(result).isEqualTo(expectedResult);
    }
}
