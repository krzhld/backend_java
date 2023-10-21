package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.lang.Math.pow;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Task8Test {
    @Test
    @DisplayName("Пустая доска")
    void emptyDesk() {
        int[][] desk = new int[8][8];
        assertThat(Task8.knightBoardCapture(desk)).isEqualTo(true);
    }

    @Test
    @DisplayName("Доска с 64 конями")
    void fullDesk() {
        int[][] desk = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };
        assertThat(Task8.knightBoardCapture(desk)).isEqualTo(false);
    }

    @Test
    @DisplayName("Доска с конями в углах")
    void cornersKnightsDesk() {
        int[][] desk = {
            {1, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 1}
        };
        assertThat(Task8.knightBoardCapture(desk)).isEqualTo(true);
    }

    @Test
    @DisplayName("Конь в каждом ряду попеременно справа и слева")
    void alternatelyRightLeftKnightDesk() {
        int[][] desk = {
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1}
        };
        assertThat(Task8.knightBoardCapture(desk)).isEqualTo(true);
    }

    @Test
    @DisplayName("Конь в углу и конь в центре")
    void cornerKnightCenterKnightDesk() {
        int[][] desk = {
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertThat(Task8.knightBoardCapture(desk)).isEqualTo(true);
    }
}
