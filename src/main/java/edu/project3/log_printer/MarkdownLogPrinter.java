package edu.project3.log_printer;

import edu.project3.Statistics;
import edu.project3.log_util.HttpStatusUtil;
import java.time.LocalDate;

public class MarkdownLogPrinter implements LogPrinter {
    private static final String TABLE_ROW_SEPARATOR = "|\n";
    private final Statistics statistics;

    public MarkdownLogPrinter(Statistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String print(
        int codesByRemoteAddressLimit,
        int mostRequiredResourcesAmount,
        int mostStatusCodesAmount
    ) {
        StringBuilder text = new StringBuilder();
        text.append("""
            #### Общая информация

            |        Метрика        |     Значение |
            |:---------------------:|-------------:|
            """);
        text.append("|       Файл(-ы)        |").append(String.join(", ", statistics.getPaths()))
            .append(TABLE_ROW_SEPARATOR);
        text.append("|    Начальная дата     |").append(getStartDate(statistics.getFrom())).append(TABLE_ROW_SEPARATOR);
        text.append("|     Конечная дата     |").append(getEndDate(statistics.getTo())).append(TABLE_ROW_SEPARATOR);
        text.append("|  Количество запросов  |").append(statistics.getTotalEntries()).append(TABLE_ROW_SEPARATOR);
        text.append("| Средний размер ответа |").append(statistics.getAverageResponseSize())
            .append(TABLE_ROW_SEPARATOR);
        text.append("""

            #### Запрашиваемые ресурсы

            |     Ресурс      | Количество |
            |:---------------:|-----------:|
            """);
        for (var entry : statistics.getFirstKMostRequiredResources(mostRequiredResourcesAmount).entrySet()) {
            text
                .append("|")
                .append(entry.getKey())
                .append("|")
                .append(entry.getValue())
                .append(TABLE_ROW_SEPARATOR);
        }
        text.append("""

            #### Коды ответа

            | Код |          Имя          | Количество |
            |:---:|:---------------------:|-----------:|
            """);
        for (var entry : statistics.getFirstKMostStatusCodes(mostStatusCodesAmount).entrySet()) {
            text
                .append("|")
                .append(entry.getKey())
                .append("|")
                .append(HttpStatusUtil.getByCode(entry.getKey()))
                .append("|")
                .append(entry.getValue())
                .append(TABLE_ROW_SEPARATOR);
        }
        return text.toString();
    }

    private String getStartDate(LocalDate date) {
        if (date.equals(LocalDate.MIN)) {
            return "-";
        } else {
            return date.toString();
        }
    }

    private String getEndDate(LocalDate date) {
        if (date.equals(LocalDate.MAX)) {
            return "-";
        } else {
            return date.toString();
        }
    }
}
