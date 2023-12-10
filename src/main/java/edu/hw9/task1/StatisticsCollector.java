package edu.hw9.task1;

import java.io.Closeable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatisticsCollector implements Closeable {

    private final Map<String, Statistics> stats;
    private final ExecutorService service;

    public StatisticsCollector(int threadsAmount) {
        this.stats = new ConcurrentHashMap<>();
        this.service = Executors.newFixedThreadPool(threadsAmount);
    }

    public void push(String metricName, double[] data) {
        Statistics metrics = stats.computeIfAbsent(metricName, v -> new Statistics(metricName));
        service.submit(() -> {
            metrics.getLock().lock();
            try {
                metrics.setNumEntries(metrics.getNumEntries() + data.length);
                metrics.setMax(Double.max(Arrays.stream(data).max().orElseThrow(), metrics.getMax()));
                metrics.setMin(Double.min(Arrays.stream(data).min().orElseThrow(), metrics.getMin()));
                metrics.setSum(Arrays.stream(data).sum() + metrics.getSum());
                metrics.setAverage(metrics.getSum() / metrics.getNumEntries());
            } finally {
                metrics.getLock().unlock();
            }
        });
    }

    public Map<String, Statistics> stats() {
        return new HashMap<>(stats);
    }

    @Override
    public void close() {
        if (service != null) {
            service.close();
        }
    }
}
