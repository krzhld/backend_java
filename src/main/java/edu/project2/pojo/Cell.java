package edu.project2.pojo;

import java.util.Objects;

public record Cell(Coordinate coordinate, Type type) {
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
        Cell cell = (Cell) o;
        return Objects.equals(this.coordinate, cell.coordinate)
            && Objects.equals(this.type, cell.type);
    }
}
