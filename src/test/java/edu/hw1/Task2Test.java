package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;

public class Task2Test {
    @Test
    @DisplayName("Ноль - это 1 цифра")
    void zeroInput() {
        int input = 0;
        assertThat(Task2.countDigits(input)).isEqualTo(1);
    }

    @Test
    @DisplayName("Шестизначное число")
    void sixDigitNumber() {
        int input = 123456;
        assertThat(Task2.countDigits(input)).isEqualTo(6);
    }
    @Test
    @DisplayName("Большая степень десятки")
    void bigPowTen() {
        long input = (long) Math.pow(10, 10);
        assertThat(Task2.countDigits(input)).isEqualTo(11);
    }

    @Test
    @DisplayName("Много нулей интерпретируются как нуль")
    void manyZeros(){
        long input = 0000000000;
        assertThat(Task2.countDigits(input)).isEqualTo(1);
    }
}
