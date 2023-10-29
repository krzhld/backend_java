package edu.hw3.StockProblem;

import java.util.Objects;

public class Stock {
    private int price;

    public Stock(int price) {
        setPrice(price);
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Invalid price!");
        }
        this.price = price;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return price == stock.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
