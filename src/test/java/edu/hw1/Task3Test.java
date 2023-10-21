package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Аргументы совпадают")
    void similarArguments() {
        int[] a = {1, 2, 3};
        int[] b = a.clone();
        assertThat(Task3.isNestable(a, b)).isEqualTo(false);
    }

    @Test
    @DisplayName("Аргументы совпадают как множества, но не как массивы")
    void similarArgumentsAsSets() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3, 3};
        assertThat(Task3.isNestable(a, b)).isEqualTo(false);
    }

    @Test
    @DisplayName("Вкладываемый массив - точка, принадлежащая второму массиву, но не граничная")
    void pointInArrayNonEdge() {
        int[] a = {2};
        int[] b = {1, 2, 3};
        assertThat(Task3.isNestable(a, b)).isEqualTo(true);
    }

    @Test
    @DisplayName("Вкладываемый массив - точка, принадлежащая второму массиву, но она максимальная")
    void pointInArrayButMax() {
        int[] a = {3};
        int[] b = {1, 2, 3};
        assertThat(Task3.isNestable(a, b)).isEqualTo(false);
    }

    @Test
    @DisplayName("Вкладываемый массив - точка, принадлежащая второму массиву, но она минимальная")
    void pointInArrayButMin() {
        int[] a = {1};
        int[] b = {1, 2, 3};
        assertThat(Task3.isNestable(a, b)).isEqualTo(false);
    }

    @Test
    @DisplayName("Оба аргументы - пустые массивы")
    void emptyArgs() {
        int[] a = {};
        int[] b = {};
        assertThat(Task3.isNestable(a, b)).isEqualTo(false);
    }

    @Test
    @DisplayName("Вкладываемый массив пуст")
    void firstArrayIsEmpty() {
        int[] a = {};
        int[] b = {1, 2, 3};
        assertThat(Task3.isNestable(a, b)).isEqualTo(false);
    }

    @Test
    @DisplayName("Массив, в который вкладывают массив, пуст")
    void secondArrayIsEmpty() {
        int[] a = {1, 2, 3};
        int[] b = {};
        assertThat(Task3.isNestable(a, b)).isEqualTo(false);
    }
}
