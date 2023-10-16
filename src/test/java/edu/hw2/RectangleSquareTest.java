package edu.hw2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import edu.hw2.rectangle_square.Rectangle;
import edu.hw2.rectangle_square.Square;

public class RectangleSquareTest {
    static Arguments[] rectangles() {
        return new Arguments[] {Arguments.of(new Rectangle()), Arguments.of(new Square())};
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Объект прямоугольник принимает параметры и корректно считает площадь. " +
        "Квадрат при изменении высоты и длины ведет себя как прямоугольник")
    void testRectangleArea(Rectangle rect) {
        rect = rect.setHeight(20);
        rect = rect.setWidth(10);
        double area = rect.area();

        Assertions.assertThat(area).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Объект квадрат для подсчета площади использует родительский метод")
    void testSquareArea() {
        int side = 8;
        Square square = new Square(side);

        double area = square.area();
        Assertions.assertThat(area).isEqualTo(64.0);
    }
}
