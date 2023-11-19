package edu.project3.log_entry;

import java.time.OffsetDateTime;
import java.util.Objects;

public record LogEntry(String remoteAddress,
                            String remoteUser,
                            OffsetDateTime timeLocal,
                            Request request,
                            HttpStatus status,
                            long bodyBytesSend,
                            String httpReferer,
                            String httpUserAgent) {

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof LogEntry)) {
            return false;
        }
        LogEntry entry = (LogEntry) o;
        return bodyBytesSend == entry.bodyBytesSend
            && Objects.equals(remoteAddress, entry.remoteAddress)
            && Objects.equals(remoteUser, entry.remoteUser)
            && Objects.equals(timeLocal, entry.timeLocal)
            && Objects.equals(request, entry.request)
            && Objects.equals(status, entry.status)
            && Objects.equals(httpReferer, entry.httpReferer)
            && Objects.equals(httpUserAgent, entry.httpUserAgent);
    }
}
