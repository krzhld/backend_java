package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter extends Thread {

    private final AtomicInteger counter;
    private final int numberIterations;

    public Counter(AtomicInteger counter, int numberIterations) {
        this.counter = counter;
        this.numberIterations = numberIterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberIterations; i++) {
            increment();
        }
    }

    public void increment() {
        counter.addAndGet(1);
    }

}
