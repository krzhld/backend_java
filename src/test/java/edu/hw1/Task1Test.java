package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Некорректное граничное количество секунд")
    void incorrectEdgeSeconds() {
        String input = "12:60";
        assertThat(Task1.minutesToSeconds(input)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Некорректное трехзачное значение секунд")
    void incorrectThreeDigitSeconds() {
        String input = "00:100";
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
}
