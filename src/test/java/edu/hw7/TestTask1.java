package edu.hw7;

import edu.hw7.task1.Runner;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask1 {
    @Test
    public void testConcurrentCounter() {
        int numberIterations = 10000;
        int numberThreads = 2;
        int numberRuns = 20;

        List<Runner> list = new ArrayList<>();
        for (int i = 0; i < numberRuns; i++) {
            list.add(new Runner(numberIterations, numberThreads));
            list.get(i).run();
        }

        int expectedValue = numberIterations * numberThreads;
        list.forEach(v -> assertThat(v.getCounterValue()).isEqualTo(expectedValue));
    }
}
