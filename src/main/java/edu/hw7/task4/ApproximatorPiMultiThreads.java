package edu.hw7.task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ApproximatorPiMultiThreads {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final int FOUR = 4;

    public double approximate(int n, int numberThreads) {
        int iterationsByThread = n / numberThreads;
        List<ApproximatorPiOneThread> threads = new ArrayList<>();
        for (int i = 0; i < numberThreads; i++) {
            threads.add(new ApproximatorPiOneThread(iterationsByThread));
            threads.get(i).start();
        }
        threads.forEach(v -> {
            try {
                v.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        int numberIterations = threads
            .stream()
            .mapToInt(ApproximatorPiOneThread::getNumberIterations)
            .sum();

        int numberPointsInCircle = threads
            .stream()
            .mapToInt(ApproximatorPiOneThread::getCircleCount)
            .sum();
        return (double) (FOUR * numberPointsInCircle) / numberIterations;
    }

    public String getMeasurements() {
        long start;
        long end;

        int threads;
        int numberSimulations;

        double approximationPi;

        DecimalFormat decimalFormat = new DecimalFormat("#.#########");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        StringBuilder stringBuilder =
            new StringBuilder("кол-во потоков\tкол-во симуляций\tрезультат\tвремя (мсек)\n");

        for (threads = 1; threads <= 4; ++threads) {
            for (numberSimulations = 1_000_000; numberSimulations <= 100_000_000; numberSimulations *= 10) {
                start = System.nanoTime();
                approximationPi = approximate(numberSimulations, threads);
                end = System.nanoTime();
                stringBuilder.append(threads).append("\t")
                    .append(numberSimulations).append("\t")
                    .append(decimalFormat.format(approximationPi)).append("\t")
                    .append((end - start) / 1e6).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
