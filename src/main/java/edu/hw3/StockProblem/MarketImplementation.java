package edu.hw3.StockProblem;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MarketImplementation implements Market {
    private static final Comparator<Stock> STOCK_COMPARATOR = Comparator.comparing(Stock::getPrice).reversed();
    private final PriorityQueue<Stock> storage;

    public MarketImplementation() {
        storage = new PriorityQueue<>(STOCK_COMPARATOR);
    }

    @Override
    public void add(Stock stock) {
        storage.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        storage.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return storage.peek();
    }
}
