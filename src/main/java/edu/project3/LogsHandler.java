package edu.project3;

import edu.project3.log_entry.Format;
import edu.project3.log_entry.LogEntry;
import edu.project3.log_util.ArgumentsParser;
import edu.project3.log_util.FileDataResolver;
import edu.project3.log_util.LogParser;
import java.time.LocalDate;
import java.util.List;

public class LogsHandler {

    private final List<String> paths;
    private final LocalDate from;
    private final LocalDate to;
    private final Format format;
    private List<LogEntry> logs;
    private Statistics statistics;

    public LogsHandler(String[] args) {
        paths = ArgumentsParser.parsePaths(args);
        from = ArgumentsParser.parseFrom(args);
        to = ArgumentsParser.parseTo(args);
        format = ArgumentsParser.parseFormat(args);
    }

    private void initLogs() {
        List<String> rawData = FileDataResolver.getAll(paths);
        logs = LogParser.parseAll(rawData);
    }

    public Statistics getStatistics() {
        if (statistics == null) {
            initLogs();
            statistics = new Statistics(paths, logs, from, to);
        }
        return statistics;
    }
}
