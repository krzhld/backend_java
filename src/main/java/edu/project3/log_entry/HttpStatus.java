package edu.project3.log_entry;

import edu.project3.log_util.HttpStatusUtil;

public class HttpStatus {

    private final int exact;
    private StatusType type;

    protected enum StatusType {
        INFO,
        SUCCESS,
        REDIRECT,
        CLIENT_ERROR,
        SERVER_ERROR
    }

    public HttpStatus(int exact) {
        this.exact = exact;
    }

    public int getExact() {
        return this.exact;
    }

    @SuppressWarnings("MagicNumber")
    public StatusType getType() {
        if (type == null) {
            type = switch (this.exact % 100) {
                case 1 -> StatusType.INFO;
                case 2 -> StatusType.SUCCESS;
                case 3 -> StatusType.REDIRECT;
                case 4 -> StatusType.CLIENT_ERROR;
                case 5 -> StatusType.SERVER_ERROR;
                default -> throw new IllegalStateException("Unexpected value: " + this.exact % 100);
            };
        }
        return type;
    }

    @Override
    public String toString() {
        return this.exact + ": " + HttpStatusUtil.getByCode(this.exact);
    }

    @SuppressWarnings("EqualsHashCode")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof HttpStatus)) {
            return false;
        }
        HttpStatus httpStatus = (HttpStatus) o;
        return this.exact == httpStatus.exact;
    }
}
