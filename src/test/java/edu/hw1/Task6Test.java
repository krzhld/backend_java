package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task6Test {
    @Test
    @DisplayName("Число Капрекара")
    void kaprekarInput() {
        int input = 6174;
        assertThat(Task6.countK(input)).isEqualTo(0);
    }

    @Test
    @DisplayName("Граничное снизу входное значение")
    void boundaryBelowInput() {
        int input = 1000;
        assertThat(Task6.countK(input)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Граничное сверху входное значение")
    void boundaryAboveInput() {
        int input = 10000;
        assertThat(Task6.countK(input)).isEqualTo(-1);
    }

    @Test
    @DisplayName("Число Капрекара, записанное наоборот")
    void near() {
        int input = 4716;
        assertThat(Task6.countK(input)).isEqualTo(1);
    }
}
