package edu.project4.pojo;

import java.util.Random;

@SuppressWarnings("MagicNumber")
public class Coefficients {
    private static final Random RANDOM_GENERATOR = new Random();
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public Coefficients() {
        do {
            this.a = (RANDOM_GENERATOR.nextDouble() - 0.5d) * 2;
            this.b = (RANDOM_GENERATOR.nextDouble() - 0.5d) * 2;
            this.c = (RANDOM_GENERATOR.nextDouble() - 0.5d) * 2;
            this.d = (RANDOM_GENERATOR.nextDouble() - 0.5d) * 2;
            this.e = (RANDOM_GENERATOR.nextDouble() - 0.5d) * 2;
            this.f = (RANDOM_GENERATOR.nextDouble() - 0.5d) * 2;
        } while (!((a * a + d * d < 1)
            && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d))));
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }
}
