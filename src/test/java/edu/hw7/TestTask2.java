package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static edu.hw7.task2.Factorial.calculateFactorial;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask2 {
    private static Stream<Arguments> calculateFactorialParameters() {
        return Stream.of(
            Arguments.of(1L, 1L),
            Arguments.of(2L, 2L),
            Arguments.of(3L, 6L),
            Arguments.of(4L, 24L),
            Arguments.of(5L, 120L),
            Arguments.of(6L, 720L),
            Arguments.of(7L, 5_040L),
            Arguments.of(8L, 40_320L),
            Arguments.of(9L, 362_880L),
            Arguments.of(10L, 3_628_800L),
            Arguments.of(11L, 39_916_800L),
            Arguments.of(12L, 479_001_600L),
            Arguments.of(13L, 6_227_020_800L),
            Arguments.of(14L, 87_178_291_200L),
            Arguments.of(15L, 1_307_674_368_000L)
        );
    }

    @ParameterizedTest
    @MethodSource("calculateFactorialParameters")
    public void testParallelFactorial(long input, long expectedResult) {
        assertThat(calculateFactorial(input)).isEqualTo(expectedResult);
    }
}
