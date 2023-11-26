package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw5.task7.RegexesFor01Alphabet.BEGIN_SYMBOL_AND_END_EQUALS;
import static edu.hw5.task7.RegexesFor01Alphabet.MINIMUM_LENGTH_IS_ONE_MAX_IS_THREE;
import static edu.hw5.task7.RegexesFor01Alphabet.MINIMUM_THREE_SYMBOLS_AND_THIRD_IS_ZERO;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask7 {
    @Test
    void testMINIMUM_THREE_SYMBOLS_AND_THIRD_IS_ZERO() {
        List<String> trueTests = List.of("000", "110", "1101110", "0001");
        List<String> falseTests = List.of("0", "001", "0010000");

        trueTests.forEach(v ->
            assertThat(MINIMUM_THREE_SYMBOLS_AND_THIRD_IS_ZERO.matcher(v).find())
                .isTrue()
        );
        falseTests.forEach(v ->
            assertThat(MINIMUM_THREE_SYMBOLS_AND_THIRD_IS_ZERO.matcher(v).find())
                .isFalse()
        );
    }

    @Test
    void testBEGIN_SYMBOL_AND_END_EQUALS() {
        List<String> trueTests = List.of("000", "111", "0101110", "1001");
        List<String> falseTests = List.of("01", "001", "1010000");

        trueTests.forEach(v ->
            assertThat(BEGIN_SYMBOL_AND_END_EQUALS.matcher(v).find())
                .isTrue()
        );
        falseTests.forEach(v ->
            assertThat(BEGIN_SYMBOL_AND_END_EQUALS.matcher(v).find())
                .isFalse()
        );
    }

    @Test
    void testMINIMUM_LENGTH_IS_ONE_MAX_IS_THREE() {
        List<String> trueTests = List.of("0", "11", "010");
        List<String> falseTests = List.of("10000", "", "1010");

        trueTests.forEach(v ->
            assertThat(MINIMUM_LENGTH_IS_ONE_MAX_IS_THREE.matcher(v).find())
                .isTrue()
        );
        falseTests.forEach(v ->
            assertThat(MINIMUM_LENGTH_IS_ONE_MAX_IS_THREE.matcher(v).find())
                .isFalse()
        );
    }
}
