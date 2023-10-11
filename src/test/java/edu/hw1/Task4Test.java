package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Пустая строка")
    void emptyString() {
        String input = "";
        assertThat(Task4.fixString(input)).isEqualTo(input);
    }

    @Test
    @DisplayName("Четная строка")
    void evenLengthString() {
        String input = "2143658709";
        assertThat(Task4.fixString(input)).isEqualTo("1234567890");
    }

    @Test
    @DisplayName("Нечетная строка")
    void oddLengthString() {
        String input = "214365870";
        assertThat(Task4.fixString(input)).isEqualTo("123456780");
    }
}
