package edu.project2.structures;

import java.util.Objects;

public record Coordinate(int row, int col) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return row == coordinate.row && col == coordinate.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
