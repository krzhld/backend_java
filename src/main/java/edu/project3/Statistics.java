package edu.project3;

import edu.project3.log_entry.HttpStatus;
import edu.project3.log_entry.LogEntry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Statistics {

    private final List<LogEntry> entries;
    private final List<String> paths;
    private final LocalDate from;
    private final LocalDate to;
    private final long totalEntries;
    private final Map<String, Long> requiredResources;
    private final Map<Integer, Long> statusCodes;
    private final long averageResponseSize;

    public Statistics(List<String> paths, List<LogEntry> entries, LocalDate from, LocalDate to) {
        this.paths = paths;
        this.from = from;
        this.to = to;
        this.entries = trimEntries(entries);
        this.totalEntries = calculateTotalEntries();
        this.requiredResources = calculateRequiredResources();
        this.statusCodes = calculateStatusCodes();
        this.averageResponseSize = calculateAverageResponseSize();
    }

    private List<LogEntry> trimEntries(List<LogEntry> entries) {
        List<LogEntry> remainEntries = new ArrayList<>(entries.size() / 2);
        for (LogEntry entry : entries) {
            if (!from.isAfter(entry.timeLocal().toLocalDate())
                && !to.isBefore(entry.timeLocal().toLocalDate())) {
                remainEntries.add(entry);
            }
        }
        return remainEntries;
    }

    private long calculateTotalEntries() {
        return entries.size();
    }

    private Map<String, Long> calculateRequiredResources() {
        return entries
            .stream()
            .collect(Collectors
                .groupingBy(
                    v -> v.request().urn(),
                    Collectors.counting()
                )
            );
    }

    private Map<Integer, Long> calculateStatusCodes() {
        return entries
            .stream()
            .collect(Collectors
                .groupingBy(
                    v -> v.status().getExact(),
                    Collectors.counting()
                )
            );
    }

    private long calculateAverageResponseSize() {
        return entries
            .stream()
            .reduce(0L, (v1, v2) -> v1 + v2.bodyBytesSend(), Long::sum) / entries.size();
    }

    private Map<String, HttpStatus> calculateMostCodesByRemoteAddress() {
        return entries
            .stream()
            .collect(Collectors.groupingBy(LogEntry::remoteAddress))
            .entrySet()
            .stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                v -> v
                    .getValue()
                    .stream()
                    .map(LogEntry::status)
                    .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    ))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey()
            ));
    }

    private long calculateUniqueUsers() {
        return entries
            .stream()
            .collect(Collectors.groupingBy(LogEntry::remoteAddress))
            .entrySet()
            .size();
    }

    public long getTotalEntries() {
        return totalEntries;
    }

    public long getAverageResponseSize() {
        return averageResponseSize;
    }

    public Map<String, Long> getFirstKMostRequiredResources(int k) {
        return requiredResources
            .entrySet()
            .stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(k)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (v1, v2) -> v1,
                LinkedHashMap::new
            ));
    }

    public Map<Integer, Long> getFirstKMostStatusCodes(int k) {
        return statusCodes
            .entrySet()
            .stream()
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
            .limit(k)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (v1, v2) -> v1,
                LinkedHashMap::new
            ));
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public List<String> getPaths() {
        return paths;
    }
}
