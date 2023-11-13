package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.hw5.task6.SearcherSubsequence.isThereSubsequenceInSequence;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask6 {
    public static Arguments[] subSequenceParameters() {
        return new Arguments[] {
            Arguments.of("abc", "achfdbaabgabcaabg", true),
            Arguments.of("sequence", "subsequence", true),
            Arguments.of("subsequence", "sequence", false),
            Arguments.of("1", "2", false),
            Arguments.of("1", "21", true),
            Arguments.of("2", "1", false),
            Arguments.of("2", "12", true)
        };
    }

    @ParameterizedTest
    @MethodSource("subSequenceParameters")
    void testSubString(String subSequence, String sequence, boolean expectedResult) {
        boolean result = isThereSubsequenceInSequence(subSequence, sequence);

        assertThat(result).isEqualTo(expectedResult);
    }

}
