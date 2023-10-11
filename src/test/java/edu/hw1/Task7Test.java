package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.lang.Math.pow;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task7Test {
    @Test
    @DisplayName("Сдвиг на ноль вправо")
    void uselessShiftRight() {
        int number = 1111;
        int shift = 0;
        assertThat(Task7.rotateRight(number, shift)).isEqualTo(number);
    }

    @Test
    @DisplayName("Сдвиг на ноль влево")
    void uselessShiftLeft() {
        int number = 1111;
        int shift = 0;
        assertThat(Task7.rotateLeft(number, shift)).isEqualTo(number);
    }

    @Test
    @DisplayName("Сдвиг на полные циклы вправо")
    void cycleShiftRight() {
        int number = (int)pow(2, 4);
        int shift = 5;
        assertThat(Task7.rotateRight(number, shift)).isEqualTo(number);
    }

    @Test
    @DisplayName("Сдвиг на полные циклы влево")
    void cycleShiftLeft() {
        int number = (int)pow(2, 5);
        int shift = 6;
        assertThat(Task7.rotateLeft(number, shift)).isEqualTo(number);
    }

    @Test
    @DisplayName("Сдвиг нуля вправо")
    void shiftZeroRight() {
        int number = 0;
        int shift = 5;
        assertThat(Task7.rotateRight(number, shift)).isEqualTo(number);
    }

    @Test
    @DisplayName("Сдвиг нуля влево")
    void shiftZeroLeft() {
        int number = 0;
        int shift = 5;
        assertThat(Task7.rotateLeft(number, shift)).isEqualTo(number);
    }

    @Test
    @DisplayName("Сдвиг вправо степени двойки как деление на степень двойки в степени сдвига")
    void shiftRightAsDivision() {
        int number = (int)pow(2, 8);
        int shift = 3;
        int expectedAnswer= number / (int)pow(2, 3);
        assertThat(Task7.rotateRight(number, shift)).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Результат сдвига влево степени двойки на единицу - единица")
    void shiftLeftMakesOne() {
        int number = (int)pow(2, 8);
        int shift = 1;
        int expectedAnswer= 1;
        assertThat(Task7.rotateLeft(number, shift)).isEqualTo(expectedAnswer);
    }

    @Test
    @DisplayName("Операции сдвига числа вправо и влево на одно число не меняют число")
    void shiftLeftRightMakesNothing() {
        int number = 123456;
        int shift = 7;
        assertThat(Task7.rotateLeft(Task7.rotateRight(number, shift), shift)).isEqualTo(number);
    }

    @Test
    @DisplayName("Операции сдвига числа влево и вправо на одно число не меняют число")
    void shiftRightLeftMakesNothing() {
        int number = 123456;
        int shift = 7;
        assertThat(Task7.rotateRight(Task7.rotateLeft(number, shift), shift)).isEqualTo(number);
    }

}
