package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.task5.CheckerRussianLicensePlates.checkAutoNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask5 {
    public static Arguments[] autoNumberParameters() {
        return new Arguments[] {
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        };
    }

    @ParameterizedTest
    @MethodSource("autoNumberParameters")
    void testCheckAutoNumber(String number, boolean expectedResult) {
        boolean result = checkAutoNumber(number);

        assertThat(result).isEqualTo(expectedResult);
    }
}
