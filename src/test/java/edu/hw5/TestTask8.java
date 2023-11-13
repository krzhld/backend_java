package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.hw5.task8.RegexesFor01AlphabetExtra.EVERY_ODD_SYMBOL_IS_ONE;
import static edu.hw5.task8.RegexesFor01AlphabetExtra.NUMBERS_ZERO_DIVISIBLE_BY_THREE;
import static edu.hw5.task8.RegexesFor01AlphabetExtra.ODD_LENGTH;
import static edu.hw5.task8.RegexesFor01AlphabetExtra.STARTS_ZERO_ODD_LENGTH_OR_STARTS_ONE_EVEN_LENGTH;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask8 {
    @Test
    void testODD_LENGTH() {
        List<String> trueTests = List.of("0", "111", "1111100");
        List<String> falseTests = List.of("10000011", "101000", "1011");

        trueTests.forEach(v ->
            assertThat(ODD_LENGTH.matcher(v).find())
                .isTrue()
        );
        falseTests.forEach(v ->
            assertThat(ODD_LENGTH.matcher(v).find())
                .isFalse()
        );
    }

    @Test
    void testSTARTS_ZERO_ODD_LENGTH_OR_STARTS_ONE_EVEN_LENGTH() {
        List<String> trueTests = List.of("000", "11", "101101");
        List<String> falseTests = List.of("100", "00", "1");

        trueTests.forEach(v ->
            assertThat(STARTS_ZERO_ODD_LENGTH_OR_STARTS_ONE_EVEN_LENGTH.matcher(v).find())
                .isTrue()
        );
        falseTests.forEach(v ->
            assertThat(STARTS_ZERO_ODD_LENGTH_OR_STARTS_ONE_EVEN_LENGTH.matcher(v).find())
                .isFalse()
        );
    }

    @Test
    void testEVERY_ODD_SYMBOL_IS_ONE() {
        List<String> trueTests = List.of("1", "101", "10101", "10111111111");
        List<String> falseTests = List.of("11000", "01100", "00010000");

        trueTests.forEach(v ->
            assertThat(EVERY_ODD_SYMBOL_IS_ONE.matcher(v).find())
                .isTrue()
        );
        falseTests.forEach(v ->
            assertThat(EVERY_ODD_SYMBOL_IS_ONE.matcher(v).find())
                .isFalse()
        );
    }

    @Test
    void testNUMBERS_ZERO_DIVISIBLE_BY_THREE() {
        List<String> trueTests = List.of("1010101000", "00011", "01100", "000");
        List<String> falseTests = List.of("0111", "1100", "011011000");

        trueTests.forEach(v ->
            assertThat(NUMBERS_ZERO_DIVISIBLE_BY_THREE.matcher(v).find())
                .isTrue()
        );
        falseTests.forEach(v ->
            assertThat(NUMBERS_ZERO_DIVISIBLE_BY_THREE.matcher(v).find())
                .isFalse()
        );
    }
}
