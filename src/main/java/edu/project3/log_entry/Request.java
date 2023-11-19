package edu.project3.log_entry;

import java.util.Objects;

public record Request(HttpMethod method, String urn, String protocol) {

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Request)) {
            return false;
        }
        Request request = (Request) o;
        return Objects.equals(this.method, request.method)
            && Objects.equals(this.urn, request.urn)
            && Objects.equals(this.protocol, request.protocol);
    }
}
