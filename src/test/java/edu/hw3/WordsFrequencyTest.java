package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Map;
import static edu.hw3.WordsFrequency.freqDict;
import static org.assertj.core.api.Assertions.assertThat;

public class WordsFrequencyTest {
    private static Arguments[] freqDictTestParams() {
        return new Arguments[] {
            Arguments.of(
                new String[] {"a", "bb", "a", "bb"},
                Map.of("bb", 2, "a", 2)
            ),
            Arguments.of(
                new Integer[] {1, 1, 2, 2},
                Map.of(1, 2, 2, 2)
            ),
            Arguments.of(
                new Double[] {1.0, 1.1, 1.2, 1.2},
                Map.of(1.0, 1, 1.1, 1, 1.2, 2)
            ),
            Arguments.of(
                new Boolean[] {true, true, true, false},
                Map.of(true, 3, false, 1)
            ),
            Arguments.of(
                new String[] {"код", "код", "код", "bug"},
                Map.of("код", 3, "bug", 1)
            )
        };
    }

    @ParameterizedTest
    @MethodSource("freqDictTestParams") <T> void testDictionary(T[] a, Map<T, Integer> expectedResult) {
        Map<T, Integer> output = freqDict(a);

        assertThat(output).isEqualTo(expectedResult);
    }
}
