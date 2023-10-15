package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task5Test {
    @Test
    @DisplayName("Однозначное число")
    void oneDigitNumber() {
        int input = 7;
        assertThat(Task5.isPalindromeDescendant(input)).isEqualTo(false);
    }

    @Test
    @DisplayName("Число с нечетным количеством одинаковых цифр")
    void oddSimilarDigitsNumber() {
        int input = 111111111;
        assertThat(Task5.isPalindromeDescendant(input)).isEqualTo(true);
    }

    @Test
    @DisplayName("Число с четным количеством одинаковых цифр")
    void evenSimilarDigitsNumber() {
        int input = 22222222;
        assertThat(Task5.isPalindromeDescendant(input)).isEqualTo(true);
    }

    @Test
    @DisplayName("Восьмизначное число особый палиндром")
    void eightDigitNumberIsDescendantPalindrome() {
        int input = 12344321;
        assertThat(Task5.isPalindromeDescendant(input)).isEqualTo(true);
    }

    @Test
    @DisplayName("Вложенный массив не в 2 раза меньше изначального. Особый палиндром")
    void lengthTwoNestedMassiveNoEqualLengthStartMassiveIsDescendantPalindrome() {
        int input = 4655;
        assertThat(Task5.isPalindromeDescendant(input)).isEqualTo(true);
    }

    @Test
    @DisplayName("Вложенный массив не в 2 раза меньше изначального. Не особый палиндром")
    void lengthTwoNestedMassiveNoEqualLengthStartMassiveIsNotDescendantPalindrome() {
        int input = 8999;
        assertThat(Task5.isPalindromeDescendant(input)).isEqualTo(false);
    }
}
