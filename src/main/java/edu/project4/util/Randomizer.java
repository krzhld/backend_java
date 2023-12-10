package edu.project4.util;

import java.util.Random;

public class Randomizer {
    private static final Random randomizer = new Random();

    public static double nextDouble(double lowerBound, double upperBound) {
        return lowerBound + (upperBound - lowerBound) * randomizer.nextDouble();
    }
}
