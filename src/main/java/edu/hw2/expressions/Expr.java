package edu.hw2.expressions;

public sealed interface Expr {
    double evaluate();

    public record Constant(double constant) implements Expr {

        @Override
        public double evaluate() {
            return this.constant;
        }

        @Override
        public String toString() {
            return String.valueOf(constant);
        }

    }

    public record Negate(Expr input) implements Expr {
        @Override
        public double evaluate() {
            return -this.input.evaluate();
        }

        @Override
        public String toString() {
            return "-" + String.valueOf(input);
        }
    }

    public record Exponent(Expr base, double degree) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(this.base.evaluate(), this.degree);
        }

        @Override
        public String toString() {
            return String.format("(%f)^(%f)", this.base.evaluate(), this.degree);
        }
    }

    public record Addition(Expr term1, Expr term2) implements Expr {
        @Override
        public double evaluate() {
            return this.term1.evaluate() + this.term2.evaluate();
        }

        @Override
        public String toString() {
            return String.valueOf(term1) + " + " + String.valueOf(term2);
        }
    }

    public record Multiplication(Expr multiplier1, Expr multiplier2) implements Expr {
        @Override
        public double evaluate() {
            return this.multiplier1.evaluate() * this.multiplier2.evaluate();
        }

        @Override
        public String toString() {
            return String.valueOf(multiplier1) + " * " + String.valueOf(multiplier2);
        }
    }
}
