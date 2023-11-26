package edu.hw7.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Runner {
    private final int numberThreads;
    public final AtomicInteger counter = new AtomicInteger();
    private final int numberIterations;

    public Runner(int numberIterations, int numberThreads) {
        this.numberIterations = numberIterations;
        this.numberThreads = numberThreads;
    }

    public void run() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberThreads; i++) {
            threads.add(new Counter(counter, numberIterations));
            threads.get(i).start();
        }
        threads.forEach(v -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public int getCounterValue() {
        return counter.get();
    }
}
