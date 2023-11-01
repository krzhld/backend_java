package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static edu.hw3.RomanNumerals.convertToRoman;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class RomanNumeralsTest {
    private static Arguments[] romanNumeralsTestParams() {
        return new Arguments[] {
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(50, "L"),
            Arguments.of(2002, "MMII"),
            Arguments.of(3999, "MMMCMXCIX")
        };
    }

    @ParameterizedTest
    @MethodSource("romanNumeralsTestParams") void testNumerals(int input, String output) {
        assertThat(convertToRoman(input)).isEqualTo(output);
    }

    @Test
    void testNegativeInput() {
        int input = -1;
        assertThatIllegalStateException().isThrownBy(() -> convertToRoman(input));
    }

    @Test
    void testBigNumberInput() {
        int input = 4000;
        assertThatIllegalStateException().isThrownBy(() -> convertToRoman(input));
    }
}
