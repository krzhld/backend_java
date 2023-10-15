package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @ParameterizedTest(name = "{index} - {0} некорректное количество секунд")
    @ValueSource(strings = {"12:60", "12:65", "12:70", "12:80", "12:100", "12:140", "12:200", "12:280"})
    void incorrectEdgeSeconds(String input) {
        assertThat(Task1.minutesToSeconds(input)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Нулевое время")
    void nullTime() {
        String input = "00:00";
        assertThat(Task1.minutesToSeconds(input)).isEqualTo(0);
    }

    @Test
    @DisplayName("Трехзначное значение минут")
    void threeDigitMinutes() {
        String input = "100:01";
        assertThat(Task1.minutesToSeconds(input)).isEqualTo(6001);
    }

    @Test
    @DisplayName("Четырехзначное значение минут")
    void fourDigitMinutes() {
        String input = "2000:02";
        assertThat(Task1.minutesToSeconds(input)).isEqualTo(120002);
    }

    @Test
    @DisplayName("Строка без двоеточия")
    void inputWithoutColon() {
        String input = "2002";
        assertThat(Task1.minutesToSeconds(input)).isEqualTo(Task1.NO_COLON);
    }

    @Test
    @DisplayName("Строка без чисел")
    void inputWithoutDigits() {
        String input = "ab:cd";
        assertThat(Task1.minutesToSeconds(input)).isEqualTo(Task1.INCORRECT_INPUT);
    }
}
