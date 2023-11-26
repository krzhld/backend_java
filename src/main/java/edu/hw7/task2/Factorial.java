package edu.hw7.task2;

import java.util.stream.LongStream;

public class Factorial {
    private Factorial() {

    }

    public static long calculateFactorial(long number) {
        return LongStream
            .iterate(1, v -> v <= number, v -> v + 1)
            .parallel()
            .reduce(1, (v1, v2) -> v1 * v2);
    }
}
