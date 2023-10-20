package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import edu.hw2.expressions.Expr;
import static edu.hw2.expressions.Expr.Addition;
import static edu.hw2.expressions.Expr.Constant;
import static edu.hw2.expressions.Expr.Exponent;
import static edu.hw2.expressions.Expr.Multiplication;
import static edu.hw2.expressions.Expr.Negate;

public class ExprTest {

    @Test
    @DisplayName("Тестовый пример")
    void testExpr() {
        Expr two = new Constant(2);
        Expr four = new Constant(4);
        Expr negOne = new Negate(new Constant(1));
        Expr sumTwoFour = new Addition(two, four);
        Expr mult = new Multiplication(sumTwoFour, negOne);
        Expr exp = new Exponent(mult, 2);
        Expr res = new Addition(exp, new Constant(1));

        double result = res.evaluate();
        assertThat(result).isEqualTo(37.0);
    }

    @Test
    @DisplayName("Два отрицания ничего не дают")
    void twoNegativeExpr() {
        Expr two = new Constant(2.0);
        Expr negTwo = new Negate(two);
        Expr negNegTwo = new Negate(negTwo);

        double result = negNegTwo.evaluate();
        assertThat(result).isEqualTo(2.0);
    }

    @Test
    @DisplayName("Сложение с отрицательным числом реализует вычитание")
    void sumNegativeExpr() {
        Expr three = new Constant(3.0);
        Expr two = new Constant(2.0);
        Expr negTwo = new Negate(two);
        Expr sum = new Addition(three, negTwo);

        double result = sum.evaluate();
        assertThat(result).isEqualTo(1.0);
    }
}
